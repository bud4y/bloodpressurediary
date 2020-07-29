package edu.progmatic.blood_pressure_diary.repositories;

import edu.progmatic.blood_pressure_diary.models.registration.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    @Query("SElECT user FROM  User user WHERE user.id=:id")
    User findUserById(@Param(value = "id")Integer id);
//    List<User> findByUsernameContaining(String username);


    User findByActivation(String code);


    User findByUsernameAndPassword(String username, String password);
}
