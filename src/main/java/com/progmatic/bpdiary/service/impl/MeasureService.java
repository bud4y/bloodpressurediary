package com.progmatic.bpdiary.service.impl;

import com.progmatic.bpdiary.web.dto.MeasureDTO;
import com.progmatic.bpdiary.model.evaluation.Condition;
import com.progmatic.bpdiary.model.evaluation.Evaluate;
import com.progmatic.bpdiary.model.evaluation.MedicalMeteorology;
import com.progmatic.bpdiary.model.evaluation.WeatherData;
import com.progmatic.bpdiary.model.measurement.MeasurementDetails;
import com.progmatic.bpdiary.model.registration.User;
import com.progmatic.bpdiary.service.MeasureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class MeasureService {
    Logger log = LoggerFactory.getLogger(MeasureService.class);
    private MeasureRepository measureRepository;

    private MedicalMeteorologyService medicalMeteorologyService;


    @Autowired
    public MeasureService(MeasureRepository measureRepository, MedicalMeteorologyService medicalMeteorologyService) {
        this.measureRepository = measureRepository;
        this.medicalMeteorologyService = medicalMeteorologyService;
    }

    @Transactional
    public MeasurementDetails persistNewMeasure(MeasureDTO measureDTO, MedicalMeteorology medicalMeteorology, Evaluate evaluate, WeatherData weatherData, Condition condition) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        User user = (User) principal;
        MeasurementDetails measure = new MeasurementDetails(measureDTO.getSystolic(), measureDTO.getDiastolic(), measureDTO.getPulse(),
                weatherData,  user, medicalMeteorology, evaluate, condition);
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedUTC = now.atZone(ZoneId.of("GMT"));
        measure.setDate(zonedUTC);
        measure.getMedicalMeteorology().setId(medicalMeteorology.getId());
        return measureRepository.save(measure);
    }

}
