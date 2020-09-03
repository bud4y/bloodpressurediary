package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.registration.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    @Query("SElECT user FROM  User user WHERE user.id=:id")
    User findUserById(@Param(value = "id")Long id);
//    List<User> findByUsernameContaining(String username);


    User findByActivation(String code);


    User findByUsernameAndPassword(String username, String password);
}
