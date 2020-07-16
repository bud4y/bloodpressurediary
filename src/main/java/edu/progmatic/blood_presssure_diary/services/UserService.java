package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.constants.Roles;
import edu.progmatic.blood_presssure_diary.dtos.UserDTO;
import edu.progmatic.blood_presssure_diary.models.registration.Role;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.repositories.RoleRepository;
import edu.progmatic.blood_presssure_diary.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService{
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;


    public boolean userNameValidation(String userName) {
        return userRepository.findByUsername(userName)!=null;
    }

    public boolean emailValidationForExistion(String email) {
        return userRepository.findByEmail(email)!=null;
    }

    public boolean emailValidationForFormat(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    public boolean passwordValidation(String password, String passwordConfirmation) {
        return passwordConfirmation.equals(password);
    }

    @Transactional
    public User createNewUser(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), bCryptPasswordEncoder.encode(userDTO.getPassword()),
                userDTO.getBirthDate(), userDTO.getEmail(), userDTO.getIsMale(), userDTO.getWeight(), userDTO.getHeight(), 0,
                new ArrayList<>(), userDTO.getUsername());

        Set<Role> roles = new HashSet<>();
        Role userRole = entityManager.createQuery("SELECT r FROM Role r WHERE r.name  = :roleName", Role.class)
                .setParameter("roleName", Roles.ROLE_USER)
                .getSingleResult();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    public void updateUserPassword(User user, String newPassword) {
        String encryptedPassword = bCryptPasswordEncoder.encode(newPassword);
        user.setPassword(encryptedPassword);
        userRepository.save(user);
    }

    public User updateUser(String lastName, String firstName, Double height, Double weight) {
//        String name = request.get("name");
//        String email = request.get("email");
//        String bio = request.get("bio");
//        appUser.setName(name);
//        appUser.setEmail(email);
//        appUser.setBio(bio);
//        appUserRepository.save(appUser);
//        mailSender.send(emailConstructor.constructUpdateUserProfileEmail(appUser));
//        return appUser;
        return null;
    }
}
