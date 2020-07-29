package edu.progmatic.blood_pressure_diary.services;

import edu.progmatic.blood_pressure_diary.constants.Roles;
import edu.progmatic.blood_pressure_diary.dtos.RegistrationDTO;
import edu.progmatic.blood_pressure_diary.dtos.UpdateExistingUserDTO;
import edu.progmatic.blood_pressure_diary.models.registration.Role;
import edu.progmatic.blood_pressure_diary.models.registration.User;
import edu.progmatic.blood_pressure_diary.repositories.RoleRepository;
import edu.progmatic.blood_pressure_diary.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
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

    private final String USER_ROLE = "USER";

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public User registerUser(RegistrationDTO registrationDTO) {
        User user = new User(registrationDTO.getFirstName(), registrationDTO.getLastName(), bCryptPasswordEncoder.encode(registrationDTO.getPassword()),
                registrationDTO.getBirthDate(), registrationDTO.getEmail(), registrationDTO.getIsMale(), registrationDTO.getWeight(), registrationDTO.getHeight(), 0,
                new ArrayList<>(), registrationDTO.getUsername());

        Set<Role> roles = new HashSet<>();
        Role userRole = entityManager.createQuery("SELECT r FROM Role r WHERE r.name  = :roleName", Role.class)
                .setParameter("roleName", Roles.ROLE_USER)
                .getSingleResult();
        roles.add(userRole);
        user.setRoles(roles);
        user.setEnabled(false);
        user.setActivation(generateKey());
        userRepository.save(user);

        return user;
    }

    public String generateKey() {
        String key = "";
        Random random = new Random();
        char[] word = new char[16];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        String toReturn = new String(word);
        //log.debug("random code: " + toReturn);
        return new String(word);
    }

    @Override
    public String userActivation(String code) {
        User user = userRepository.findByActivation(code);
        if (user == null)
            return "noresult";

        user.setEnabled(true);
        user.setActivation("");
        userRepository.save(user);
        return "ok";
    }


    public List<User> userList() {
        return userRepository.findAll();
    }

    public User findById(Integer id) {
        return userRepository.findUserById(Math.toIntExact(id));
    }

    public List<User> findAllUser() {
        return userRepository.findAll();
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

    public User setProfilePicture(Long pictureId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        User user = (User) principal;
        user.setPictureId(pictureId);
        userRepository.save(user);
        return user;
    }
}
