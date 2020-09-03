package com.progmatic.bpdiary.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UpdateExistingUserDTO {
    @Size(min = 5,max=10)
    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirmation;
    private Double weight;
    private Double height;

    @Override
    public String toString() {
        return "UpdateExistingUserDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
}
