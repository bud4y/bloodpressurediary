package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.dtos.UserDTO;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;


@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users")
    public UserDTO registerNewUser(@ModelAttribute(value = "newUser") @Valid @RequestBody UserDTO newUser,
                                          BindingResult bindingResult) throws Exception {
   logger.info("post method  called");
        if (bindingResult.hasErrors()) {
            throw new Exception("hoppa");
        }
        if (userService.userNameValidation(newUser.getUsername())) {
            bindingResult.addError(new FieldError("newUser", "username", "A felhasználó név már foglalt!"));
            throw new Exception("hoppa");
        } else if (!userService.passwordValidation(newUser.getPassword(), newUser.getPasswordConfirmation())) {
            bindingResult.addError(new FieldError("newUser", "password", "Nem egyezik a két jelszó"));
            throw new Exception("hoppa");
        } else if (userService.emailValidation(newUser.getEmail())) {
            bindingResult.addError(new FieldError("newUser", "email", "Az email cim már foglalt"));
            throw new Exception("hoppa");
        } else {
            userService.createNewUser(newUser);
            return newUser;
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {

        try {
            User appUser = userService.createNewUser(userDTO);
            return new ResponseEntity<>(appUser, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>("An Error Occurred", HttpStatus.BAD_REQUEST);
        }
    }
}
