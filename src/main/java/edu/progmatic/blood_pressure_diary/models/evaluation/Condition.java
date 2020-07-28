package edu.progmatic.blood_pressure_diary.models.evaluation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.progmatic.blood_pressure_diary.models.measurement.MeasurementDetails;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "condition_table")
public class Condition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "condition_column")
    private String condition;
    @OneToMany
    @JsonIgnore
    List<MeasurementDetails> measurementDetails;

    public Condition() {

    }

    public Condition(String condition) {
        this.condition = condition;
    }
}
