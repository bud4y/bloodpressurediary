package edu.progmatic.bpdiary.service;


import edu.progmatic.bpdiary.web.dto.RegistrationDTO;
import edu.progmatic.bpdiary.model.registration.User;

public interface UserService {

	public User registerUser(RegistrationDTO user);

	public User findByEmail(String email);

	public String userActivation(String code);

	
}
