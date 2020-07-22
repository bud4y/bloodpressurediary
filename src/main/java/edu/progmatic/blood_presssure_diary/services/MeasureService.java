package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.dtos.MeasureDTO;
import edu.progmatic.blood_presssure_diary.models.evaluation.BloodPressureValue;
import edu.progmatic.blood_presssure_diary.models.evaluation.Evaluate;
import edu.progmatic.blood_presssure_diary.models.evaluation.MedicalMeteorology;
import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.repositories.BloodPressureValueRepository;
import edu.progmatic.blood_presssure_diary.repositories.MeasureRepository;
import edu.progmatic.blood_presssure_diary.webscrap.WebScrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.*;
import java.util.Date;

@Service
public class MeasureService {
    Logger log = LoggerFactory.getLogger(MeasureService.class);
    private MeasureRepository measureRepository;
    private BloodPressureValueRepository bloodPressureValueRepository;


    @Autowired
    public MeasureService(MeasureRepository measureRepository, BloodPressureValueRepository bloodPressureValueRepository) {
        this.measureRepository = measureRepository;
        this.bloodPressureValueRepository = bloodPressureValueRepository;
    }

    @Transactional
    public MeasurementDetails persistNewMeasure(MeasureDTO measureDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        User user = (User) principal;
        MeasurementDetails measure = new MeasurementDetails(measureDTO.getSystolic(), measureDTO.getDiastolic(), measureDTO.getPulse(), user);
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedUTC = now.atZone(ZoneId.of("GMT"));
        measure.setDate(zonedUTC);
        return measureRepository.save(measure);
    }

    public BloodPressureValue evaluateMeasurement(MeasurementDetails measurementDetails) {
        User user = measurementDetails.getUser();
        int systolicValue = measurementDetails.getSystolicValue();
        int diastolicValue = measurementDetails.getDiastolicValue();
        int userAge = Math.abs(user.getBirthDate().getYear() - LocalDate.now().getYear());
        log.debug(userAge + " kor");

        BloodPressureValue byAge = bloodPressureValueRepository.findByAge(userAge);
        return byAge;
    }
}
