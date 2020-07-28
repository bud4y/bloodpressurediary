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
        WeatherData weatherData = new WeatherData(data.get("humidity").toString(), data.get("temp").toString(), data.get("pressure").toString());
        return weatherRepository.save(weatherData);
    }
}
