package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.constants.Roles;
import edu.progmatic.blood_presssure_diary.dtos.UserDTO;
import edu.progmatic.blood_presssure_diary.models.registration.Role;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public boolean userNameValidation(String userName) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :userName", User.class)
                .setParameter("userName", userName).getResultList();
        return !users.isEmpty();
    }

    public boolean emailValidationForExistion(String email) {
        List<User> emails = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email).getResultList();
        return !emails.isEmpty();
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
                userDTO.getBirthDate(), userDTO.getEmail(), userDTO.isMale(), userDTO.getWeight(), userDTO.getHeight(), 0,
                new ArrayList<>(), userDTO.getUsername());

        Set<Role> roles = new HashSet<>();
        Role userRole = entityManager.createQuery("SELECT r FROM Role r WHERE r.name  = :roleName", Role.class)
                .setParameter("roleName", Roles.ROLE_USER)
                .getSingleResult();
        roles.add(userRole);
        user.setRoles(roles);
        logger.info(roles + " ez a role");
        logger.info(user + " service adatok");
        entityManager.persist(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }
}
