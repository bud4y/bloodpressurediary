package edu.progmatic.blood_presssure_diary.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateExistingUserDTO {
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
