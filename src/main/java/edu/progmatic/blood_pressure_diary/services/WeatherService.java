package edu.progmatic.blood_pressure_diary.services;

import edu.progmatic.blood_pressure_diary.models.evaluation.WeatherData;
import edu.progmatic.blood_pressure_diary.repositories.WeatherRepository;
import edu.progmatic.blood_pressure_diary.weather.DailyWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WeatherService {
    private DailyWeather weather;
    private WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(DailyWeather weather, WeatherRepository weatherRepository){
        this.weather = weather;
        this.weatherRepository = weatherRepository;
    }

    public WeatherData persistDailyWeatherData(){
        Map<String, Object> data = weather.getData();
        String temp = data.get("temp").toString();
        Double d = Double.parseDouble(temp);
        d = (d-32)/1.8;
        String result = String.format("%.2f", d);
        WeatherData weatherData = new WeatherData(data.get("humidity").toString(), result, data.get("pressure").toString());
        return weatherRepository.save(weatherData);
    }
}
