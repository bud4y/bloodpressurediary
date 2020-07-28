package edu.progmatic.blood_pressure_diary.repositories;

import edu.progmatic.blood_pressure_diary.models.evaluation.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
}
