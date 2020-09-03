package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.evaluation.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
}
