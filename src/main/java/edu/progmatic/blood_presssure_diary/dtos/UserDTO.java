package edu.progmatic.blood_presssure_diary.dtos;

import edu.progmatic.blood_presssure_diary.validators.password.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserDTO {
    @Size(min = 3, max = 20)
    @NotNull
    private String username;
    @Size(min = 3, max = 20)
    @NotNull
    private String firstName;
    @Size(min = 3, max = 20)
    @NotNull
    private String lastName;
    @ValidPassword
    private String password;
    @ValidPassword
    private String passwordConfirmation;
    @NotNull
    private LocalDate birthDate;
    private String email;
    private @NotNull Boolean isMale;
    @NotNull
    private  Double weight;
    @NotNull
    private  Double height;

    public UserDTO(@Size(min = 3, max = 20) @NotNull String username, @Size(min = 3, max = 20) @NotNull String firstName, @Size(min = 3, max = 20) @NotNull String lastName, String password, String passwordConfirmation, @NotNull LocalDate birthDate, String email, @NotNull Boolean isMale, @NotNull Double weight, @NotNull Double height) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.birthDate = birthDate;
        this.email = email;
        this.isMale = isMale;
        this.weight = weight;
        this.height = height;
    }

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

    public Boolean isMale() {
        return isMale;
    }

    public void setMale(Boolean male) {
        isMale = male;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight( Double weight) {
        this.weight = weight;
    }

    public  Double getHeight() {
        return height;
    }

    public void setHeight( Double height) {
        this.height = height;
    }
}
