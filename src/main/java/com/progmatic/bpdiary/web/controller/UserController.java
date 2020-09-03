package com.progmatic.bpdiary.web.controller;

import com.progmatic.bpdiary.service.impl.EmailService;
import com.progmatic.bpdiary.validators.PasswordValidatorForUpdate;
import com.progmatic.bpdiary.web.dto.RegistrationDTO;
import com.progmatic.bpdiary.web.dto.UpdateExistingUserDTO;
import com.progmatic.bpdiary.model.registration.UserAuthenticationService;
import com.progmatic.bpdiary.model.registration.User;
import com.progmatic.bpdiary.service.impl.UserServiceImpl;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
//@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @NonNull
    UserAuthenticationService authentication;
    private UserServiceImpl userServiceImpl;
    private PasswordValidatorForUpdate passwordValidatorForUpdate;
    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    public UserController(UserServiceImpl userServiceImpl, PasswordValidatorForUpdate passwordValidatorForUpdate) {
        this.userServiceImpl = userServiceImpl;
        this.passwordValidatorForUpdate = passwordValidatorForUpdate;
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationDTO registrationDTO) {
        logger.debug(registrationDTO + " felhasznalo");
        System.out.println((registrationDTO + " felhasznalo"));
        if (userServiceImpl.userNameValidation(registrationDTO.getUsername())) {
            return new ResponseEntity<>("Username Exists", HttpStatus.CONFLICT);
        } else if (!userServiceImpl.passwordValidation(registrationDTO.getPassword(), registrationDTO.getPasswordConfirmation())) {
            return new ResponseEntity<>("Passwords are not matching", HttpStatus.CONFLICT);
        } else if (userServiceImpl.emailValidationForExistion(registrationDTO.getEmail())) {
            return new ResponseEntity<>("Email Exists", HttpStatus.CONFLICT);
        } else if (userServiceImpl.emailValidationForFormat(registrationDTO.getEmail())) {
            return new ResponseEntity<>("Email invalid format", HttpStatus.CONFLICT);
        } else {
            try {

                User appUser = userServiceImpl.registerUser(registrationDTO);
                emailService.sendMessage(registrationDTO.getEmail(), registrationDTO.getUsername(), appUser.getActivation());
                return new ResponseEntity<>(appUser, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("An Error Occurred", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id){
        return userServiceImpl.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateExistingUserDTO updateUserDTO, @PathVariable Long id) {

        User user = userServiceImpl.findById(id);
        if (updateUserDTO.getPassword() == null && updateUserDTO.getPasswordConfirmation() == null) {
            return new ResponseEntity<>("Passwords is incorrect", HttpStatus.CONFLICT);
        }
        if (user == null) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        if (!userServiceImpl.passwordValidation(updateUserDTO.getPassword(),
                updateUserDTO.getPasswordConfirmation())) {
            return new ResponseEntity<>("Passwords are not matching", HttpStatus.CONFLICT);
        }
        PasswordValidator validator = new PasswordValidator(passwordValidatorForUpdate.getRules());
        PasswordData password = new PasswordData(updateUserDTO.getPassword());
        RuleResult result = validator.validate(password);
        if (result.isValid()) {
            return new ResponseEntity<>("Password is invalid", HttpStatus.NOT_FOUND);
        } else {
            try {
                userServiceImpl.updateUser(updateUserDTO, id);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("An Error Occurred", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @RequestMapping(path = "/user/activation/{code}", method = RequestMethod.GET)
    public void activation(@PathVariable("code") String code, HttpServletResponse response) throws IOException {
        userServiceImpl.userActivation(code);
         response.sendRedirect("http://localhost:4200/#/emailconfirm");
    }


    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public User loginUser(@RequestBody RegistrationDTO registrationDTO) throws Exception {
        String username = registrationDTO.getUsername();
        String password = registrationDTO.getPassword();
        User obj = null;
        if (username != null && password != null) {
            obj = userServiceImpl.fetchUserByUsernameAndPassword(username, password);
        }
        return obj;
    }

    @GetMapping("/login_user")
    public ResponseEntity<?> isUserLoggedIn() {
        if (userServiceImpl.isAuthenticated()) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
        }
    }
}
