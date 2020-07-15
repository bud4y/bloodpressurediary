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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean userNameValidation(String userName) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.name = :userName", User.class)
                .setParameter("userName", userName).getResultList();
        return !users.isEmpty();
    }

    public boolean emailValidation(String email) {
        List<User> emails = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email).getResultList();
        return !emails.isEmpty();
    }

    public boolean passwordValidation(String password, String passwordConfirmation) {
        return passwordConfirmation.equals(password);
    }

    @Transactional
    public void createNewUser(UserDTO userDTO) {
        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), bCryptPasswordEncoder.encode(userDTO.getPassword()),
                userDTO.getBirthDate(), userDTO.getEmail(), userDTO.isMale(), userDTO.getWeight(), userDTO.getHeight(), 0,
                new ArrayList<>(), userDTO.getUsername());

        Set<Role> roles = new HashSet<>();
        Role userRole = entityManager.createQuery("SELECT r FROM Role r WHERE r.name  = :roleName", Role.class)
                .setParameter("roleName", Roles.ROLE_USER)
                .getSingleResult();
        roles.add(userRole);
        user.setRoles(roles);
        logger.info(user+" service adatok");
        entityManager.persist(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username")
                .setParameter("username", username)
                .getSingleResult();
    }
}
