package edu.progmatic.blood_presssure_diary.dtos;

import edu.progmatic.blood_presssure_diary.constants.DateFormats;
import edu.progmatic.blood_presssure_diary.validators.password.ValidPassword;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserDTO {
    @Size(min = 3, max = 20)
    @NotEmpty
    private String username;
    @Size(min = 3, max = 20)
    @NotEmpty
    private String firstName;
    @Size(min = 3, max = 20)
    @NotEmpty
    private String lastName;
    @ValidPassword
    private String password;
    @ValidPassword
    private String passwordConfirmation;
    @NotNull
    @DateTimeFormat(pattern = DateFormats.DATE_FORMAT)
    private LocalDate birthDate;
    @NotEmpty
    private String email;
    @NotNull
    private boolean isMale;
    @NotEmpty
    private double weight;
    @NotEmpty
    private double height;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
