package edu.progmatic.blood_pressure_diary.services;

import edu.progmatic.blood_pressure_diary.dtos.MeasureDTO;
import edu.progmatic.blood_pressure_diary.models.evaluation.Condition;
import edu.progmatic.blood_pressure_diary.models.evaluation.Evaluate;
import edu.progmatic.blood_pressure_diary.models.evaluation.MedicalMeteorology;
import edu.progmatic.blood_pressure_diary.models.evaluation.WeatherData;
import edu.progmatic.blood_pressure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_pressure_diary.models.registration.User;
import edu.progmatic.blood_pressure_diary.repositories.MeasureRepository;
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
