package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.dtos.RegistrationDTO;
import edu.progmatic.blood_presssure_diary.dtos.UpdateExistingUserDTO;
import edu.progmatic.blood_presssure_diary.services.EmailService;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.services.UserServiceImpl;
import edu.progmatic.blood_presssure_diary.validators.password.PasswordValidatorForUpdate;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
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
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationDTO registrationDTO) {
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
                emailService.sendMessage(registrationDTO.getEmail(),registrationDTO.getUsername());
                User appUser = userServiceImpl.registerUser(registrationDTO);

                return new ResponseEntity<>(appUser, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("An Error Occurred", HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateExistingUserDTO updateUserDTO, @PathVariable Integer id) {

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

    @RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
    public String activation(@PathVariable("code") String code, HttpServletResponse response) {
        String result = userServiceImpl.userActivation(code);
        return "auth/login";
    }
}
