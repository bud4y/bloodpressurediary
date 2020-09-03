package com.progmatic.bpdiary.model.evaluation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progmatic.bpdiary.model.measurement.MeasurementDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MedicalMeteorology {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private ZonedDateTime date;
    @Size(max = 1000)
    private String text;
    @OneToMany(mappedBy = "medicalMeteorology")
    @JsonIgnore
    private List<MeasurementDetails> measurementDetails;
    public MedicalMeteorology(ZonedDateTime date, String text) {
        this.date = date;
        this.text = text;
    }
}
