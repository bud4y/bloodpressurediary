package edu.progmatic.blood_pressure_diary.models.registration;

import java.util.Optional;

public interface UserCrudService {


    User save(User user);

    Optional<User> find(String id);

    Optional<User> findByUsername(String username);
}