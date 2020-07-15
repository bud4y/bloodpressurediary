package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.dtos.UserDTO;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
        if (userService.userNameValidation(userDTO.getUsername())) {
            return new ResponseEntity<>("Username Exists", HttpStatus.CONFLICT);
        } else if (!userService.passwordValidation(userDTO.getPassword(), userDTO.getPasswordConfirmation())) {
            return new ResponseEntity<>("Passwords are not matching", HttpStatus.CONFLICT);
        } else if (userService.emailValidation(userDTO.getEmail())) {
            return new ResponseEntity<>("Email Exists", HttpStatus.CONFLICT);
        } else {
            try {
                User appUser = userService.createNewUser(userDTO);
                return new ResponseEntity<>(appUser, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("An Error Occurred", HttpStatus.BAD_REQUEST);
            }
        }
    }
}
