package edu.progmatic.blood_presssure_diary.models;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate date;
    @OneToOne
    private Advice advice;
    @ManyToOne
    private User user;
    public MeasurementDetails() {
    }

    public MeasurementDetails(int systolicValue, int diastolicValue, int pulsePerMin, WeatherData wd, LocalDate date, Advice advice, User user) {
        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
        this.pulsePerMin = pulsePerMin;
        this.wd = wd;
        this.date = date;
        this.advice = advice;
        this.user = user;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public WeatherData getWd() {
        return wd;
    }

    public void setWd(WeatherData wd) {
        this.wd = wd;
    }
}
