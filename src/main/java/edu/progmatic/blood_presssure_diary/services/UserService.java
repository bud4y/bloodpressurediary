package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.constants.Roles;
import edu.progmatic.blood_presssure_diary.dtos.RegistrationDTO;
import edu.progmatic.blood_presssure_diary.dtos.UpdateExistingUserDTO;
import edu.progmatic.blood_presssure_diary.models.registration.Role;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.repositories.RoleRepository;
import edu.progmatic.blood_presssure_diary.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @PersistenceContext
    EntityManager entityManager;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean userNameValidation(String userName) {
        return userRepository.findByUsername(userName) != null;
    }

    public boolean emailValidationForExistion(String email) {
        return userRepository.findByEmail(email) != null;
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
    public User createNewUser(RegistrationDTO registrationDTO) {
        User user = new User(registrationDTO.getFirstName(), registrationDTO.getLastName(), bCryptPasswordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getBirthDate(), registrationDTO.getEmail(), registrationDTO.getIsMale(), registrationDTO.getWeight(), registrationDTO.getHeight(), 0,
                new ArrayList<>(), registrationDTO.getUsername());

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

    public User findById(Integer id) {
        return userRepository.findUserById(Math.toIntExact(id));
    }


    public User updateUser(UpdateExistingUserDTO userUpdateDTO, Integer id) {
        User user = userRepository.findUserById(id);

        if (!userUpdateDTO.getLastName().equals(user.getLastName())) {
            user.setLastName(userUpdateDTO.getLastName());
        }
        if (!userUpdateDTO.getFirstName().equals(user.getFirstName())) {
            user.setFirstName(userUpdateDTO.getFirstName());
        }
        if (!userUpdateDTO.getHeight().equals(user.getHeight())) {
            user.setHeight(userUpdateDTO.getHeight());
        }
        if (!userUpdateDTO.getWeight().equals(user.getWeight())) {
            user.setWeight(userUpdateDTO.getWeight());
        }
        if (!userUpdateDTO.getPassword().equals(bCryptPasswordEncoder.encode(user.getPassword()))) {
            String encryptedPassword = bCryptPasswordEncoder.encode(userUpdateDTO.getPassword());
            user.setPassword(encryptedPassword);
        }
        userRepository.save(user);
        return user;
    }

    public User setProfilePicture(Integer pictureId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        User user = (User) principal;
        user.setPictureId(pictureId);
        userRepository.save(user);
        return user;
    }
}
