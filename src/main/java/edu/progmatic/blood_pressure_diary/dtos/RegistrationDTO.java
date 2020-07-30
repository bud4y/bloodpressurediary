package edu.progmatic.blood_pressure_diary.dtos;

import edu.progmatic.blood_pressure_diary.validators.password.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDTO {
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
    @NotNull
    private String email;
    private  Boolean isMale;
    @NotNull
    private Double weight;
    @NotNull
    private Double height;
    private String activation;
    private Boolean enabled;

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", isMale=" + isMale +
                ", weight=" + weight +
                ", height=" + height +
                '}';
    }
    //
}
