package edu.progmatic.blood_presssure_diary.models.measurement;

import edu.progmatic.blood_presssure_diary.models.evaluation.Evaluate;
import edu.progmatic.blood_presssure_diary.models.evaluation.WeatherData;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
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

    public MeasurementDetails(int systolicValue, int diastolicValue, int pulsePerMin, User user) {
        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
        this.pulsePerMin = pulsePerMin;
        this.user = user;
    }

    @Override
    public String toString() {
        return "MeasurementDetails{" +
                "id=" + id +
                ", systolicValue=" + systolicValue +
                ", diastolicValue=" + diastolicValue +
                ", pulsePerMin=" + pulsePerMin +
                ", wd=" + wd +
                ", date=" + date +
                ", evaluate=" + evaluate +
                ", user=" + user +
                '}';
    }
}
