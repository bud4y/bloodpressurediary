package edu.progmatic.blood_presssure_diary.services;


import edu.progmatic.blood_presssure_diary.dtos.RegistrationDTO;
import edu.progmatic.blood_presssure_diary.models.registration.User;

public interface UserService {

	public User registerUser(RegistrationDTO user);

	public User findByEmail(String email);

	public String userActivation(String code);
	
}
