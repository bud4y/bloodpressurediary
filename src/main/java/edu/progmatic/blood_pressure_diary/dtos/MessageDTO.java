package edu.progmatic.blood_pressure_diary.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class MessageDTO {

    public Long postId;
    @NotNull
    public String text;

}
