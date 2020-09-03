package edu.progmatic.bpdiary.service;

import edu.progmatic.bpdiary.model.evaluation.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
}
