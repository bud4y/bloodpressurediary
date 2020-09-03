package com.progmatic.bpdiary.service;


import com.progmatic.bpdiary.model.registration.User;
import com.progmatic.bpdiary.web.dto.RegistrationDTO;

public interface UserService {

	public User registerUser(RegistrationDTO user);

	public User findByEmail(String email);

	public String userActivation(String code);

	
}
