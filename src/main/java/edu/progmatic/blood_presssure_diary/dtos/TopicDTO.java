package edu.progmatic.blood_presssure_diary.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TopicDTO {

    @NotNull
    private Long id;

    @NotEmpty
    private String topicName;

    @NotEmpty
    private String topicText;
}
