package edu.progmatic.blood_presssure_diary.dtos;

import edu.progmatic.blood_presssure_diary.validators.password.ValidPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
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
    private Double weight;
    @NotNull
    private Double height;


}
