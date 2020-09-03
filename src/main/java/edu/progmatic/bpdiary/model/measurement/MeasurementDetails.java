package edu.progmatic.bpdiary.model.measurement;

import edu.progmatic.bpdiary.model.evaluation.Condition;
import edu.progmatic.bpdiary.model.evaluation.Evaluate;
import edu.progmatic.bpdiary.model.evaluation.MedicalMeteorology;
import edu.progmatic.bpdiary.model.evaluation.WeatherData;
import edu.progmatic.bpdiary.model.registration.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class MeasurementDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private int systolicValue;
    private int diastolicValue;
    private int pulsePerMin;
    @OneToOne
    private WeatherData wd;
    private ZonedDateTime date;
    @OneToOne
    private Evaluate evaluate;
    @ManyToOne
    private User user;
    @ManyToOne
    MedicalMeteorology medicalMeteorology;
    @ManyToOne
    Condition condition;

    public MeasurementDetails() {
    }

    public MeasurementDetails(
            int systolicValue,
            int diastolicValue,
            int pulsePerMin,
            WeatherData wd,
            User user,
            MedicalMeteorology medicalMeteorology,
            Evaluate evaluate, Condition condition) {

        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
        this.pulsePerMin = pulsePerMin;
        this.wd = wd;
        this.evaluate = evaluate;
        this.user = user;
        this.medicalMeteorology = medicalMeteorology;
        this.condition = condition;
    }

}
