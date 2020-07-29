package edu.progmatic.blood_pressure_diary.services;


import edu.progmatic.blood_pressure_diary.dtos.RegistrationDTO;
import edu.progmatic.blood_pressure_diary.models.registration.User;

import java.util.Optional;

public interface UserService {

	public User registerUser(RegistrationDTO user);

	public User findByEmail(String email);

	public String userActivation(String code);

	
}
