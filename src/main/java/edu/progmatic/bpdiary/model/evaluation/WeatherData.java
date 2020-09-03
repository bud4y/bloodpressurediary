package edu.progmatic.bpdiary.model.evaluation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class WeatherData {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    private String humidity;
    private String temperature;
    private String airPressure;

    public WeatherData(){
    }

    public WeatherData(String humidity, String temperature, String airPressure) {
        this.humidity = humidity;
        this.temperature = temperature;
        this.airPressure = airPressure;
    }
}
