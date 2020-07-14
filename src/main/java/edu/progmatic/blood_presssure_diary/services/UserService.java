package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.constants.Roles;
import edu.progmatic.blood_presssure_diary.dtos.UserDTO;
import edu.progmatic.blood_presssure_diary.models.registration.Role;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public void createNewUser(UserDTO userDTO){
        User user  = new User(userDTO.getFirstName(),userDTO.getLastName(),userDTO.getPassword(),userDTO.getBirthDate(),
                userDTO.getEmail(),userDTO.isMale(),userDTO.getWeight(),userDTO.getHeight(),0,
                new ArrayList<>(),userDTO.getUsername());
        //measurement lista??bmi??

        Set<Role> roles = new HashSet<>();
        Role userRole = em.createQuery("SELECT r FROM Role r WHERE r.name  = :roleName", Role.class)
                .setParameter("roleName", Roles.ROLE_USER )
                .getSingleResult();
        roles.add(userRole);
        user.setRoles(roles);
        em.persist(user);
    }
}
