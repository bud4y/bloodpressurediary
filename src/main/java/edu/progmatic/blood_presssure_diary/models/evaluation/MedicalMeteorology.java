package edu.progmatic.blood_presssure_diary.models.evaluation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MedicalMeteorology {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private ZonedDateTime date;
    private String text;

    public MedicalMeteorology(ZonedDateTime date, String text) {
        this.date = date;
        this.text = text;
    }
}
