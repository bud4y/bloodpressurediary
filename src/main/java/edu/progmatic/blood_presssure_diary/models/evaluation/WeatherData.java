package edu.progmatic.blood_presssure_diary.models.evaluation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WeatherData {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    private double humidity;
    private double temperature;
    private double airPressure;
    private String med_meteorology;

    public WeatherData(){
    }

    public WeatherData(double humidity, double temperature, double airPressure, String med_meteorology) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.airPressure = airPressure;
        this.med_meteorology = med_meteorology;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }

    public String getMed_meteorology() {
        return med_meteorology;
    }

    public void setMed_meteorology(String frontInfo) {
        this.med_meteorology = frontInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
