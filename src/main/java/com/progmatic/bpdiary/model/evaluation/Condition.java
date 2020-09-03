package com.progmatic.bpdiary.model.evaluation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progmatic.bpdiary.model.measurement.MeasurementDetails;
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
    @OneToMany(mappedBy = "condition")
    @JsonIgnore
    List<MeasurementDetails> measurementDetails;

    public Condition() {

    }

    public Condition(String condition) {
        this.condition = condition;
    }
}
