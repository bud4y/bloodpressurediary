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
    private String frontInfo;

    public WeatherData(){
    }

    public WeatherData(double humidity, double temperature, double airPressure, String frontInfo) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.airPressure = airPressure;
        this.frontInfo = frontInfo;
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

    public String getFrontInfo() {
        return frontInfo;
    }

    public void setFrontInfo(String frontInfo) {
        this.frontInfo = frontInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
