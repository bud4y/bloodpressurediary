package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.dtos.MeasureDTO;
import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_presssure_diary.models.registration.User;
import edu.progmatic.blood_presssure_diary.repositories.MeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.*;

@Service
public class MeasureService {
    MeasureRepository measureRepository;

    @Autowired
    public MeasureService(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    @Transactional
    public MeasurementDetails persistNewMeasure(MeasureDTO measureDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        User user = (User) principal;
        MeasurementDetails measure = new MeasurementDetails(measureDTO.getSystolic(), measureDTO.getDiastolic(), measureDTO.getSystolic());
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedUTC = now.atZone(ZoneId.of("GMT"));
        measure.setDate(zonedUTC);
        return measureRepository.save(measure);
    }
}
