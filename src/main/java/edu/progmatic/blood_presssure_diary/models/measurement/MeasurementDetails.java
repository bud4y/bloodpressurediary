package edu.progmatic.blood_presssure_diary.models.measurement;

import edu.progmatic.blood_presssure_diary.models.evaluation.Evaluate;
import edu.progmatic.blood_presssure_diary.models.evaluation.WeatherData;
import edu.progmatic.blood_presssure_diary.models.registration.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
public class MeasurementDetails {
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    public MeasurementDetails() {
    }

    public MeasurementDetails(int systolicValue, int diastolicValue, int pulsePerMin/*, WeatherData wd, LocalDate date, Evaluate evaluate, User user*/ ) {
        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
        this.pulsePerMin = pulsePerMin;
        /*this.wd = wd;
        this.date = date;
        this.evaluate = evaluate;
        this.user = user;*/
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSystolicValue() {
        return systolicValue;
    }

    public void setSystolicValue(int systolicValue) {
        this.systolicValue = systolicValue;
    }

    public int getDiastolicValue() {
        return diastolicValue;
    }

    public void setDiastolicValue(int diastolicValue) {
        this.diastolicValue = diastolicValue;
    }

    public int getPulsePerMin() {
        return pulsePerMin;
    }

    public void setPulsePerMin(int pulsePerMin) {
        this.pulsePerMin = pulsePerMin;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evaluate getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(Evaluate advice) {
        this.evaluate = advice;
    }

    public WeatherData getWd() {
        return wd;
    }

    public void setWd(WeatherData wd) {
        this.wd = wd;
    }
}
