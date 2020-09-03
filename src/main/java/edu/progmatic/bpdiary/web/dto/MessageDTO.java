package edu.progmatic.bpdiary.web.dto;

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
