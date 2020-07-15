package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.dtos.UserDTO;
import edu.progmatic.blood_presssure_diary.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/registration")
    public UserDTO viewRegistration(Model model) {
        System.out.println("get method called");
        UserDTO newUser = new UserDTO();
        model.addAttribute("newUser", newUser);
        return newUser;
    }

    @PostMapping(value = "/registration")
    public UserDTO registerNewUser(@ModelAttribute(value = "newUser") @Valid UserDTO newUser,BindingResult bindingResult) {
        System.out.println("bent vagyok a controllerben");
        if (bindingResult.hasErrors()) {
            return newUser;
        }
        if (userService.userNameValidation(newUser.getUsername())) {
            bindingResult.addError(new FieldError("newUser", "username", "A felhasználó név már foglalt!"));
            return newUser;
        } else if (!userService.passwordValidation(newUser.getPassword(), newUser.getPasswordConfirmation())) {
            bindingResult.addError(new FieldError("newUser", "password", "Nem egyezik a két jelszó"));
            return newUser;
        } else if (userService.emailValidation(newUser.getEmail())) {
            bindingResult.addError(new FieldError("newUser", "email", "Az email cim már foglalt"));
            return newUser;
        } else {
            userService.createNewUser(newUser);
            return newUser;
        }
    }
}
