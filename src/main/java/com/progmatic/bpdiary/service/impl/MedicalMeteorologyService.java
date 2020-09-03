package com.progmatic.bpdiary.service.impl;

import com.progmatic.bpdiary.model.evaluation.MedicalMeteorology;
import com.progmatic.bpdiary.service.MedicalMeteorologyRepository;
import com.progmatic.bpdiary.web.webscrap.WebScrap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class MedicalMeteorologyService {
    private MedicalMeteorologyRepository medicalMeteorologyRepository;
    private WebScrap webScrap;

    @Autowired
    public MedicalMeteorologyService(MedicalMeteorologyRepository medicalMeteorologyRepository, WebScrap webScrap) {
        this.medicalMeteorologyRepository = medicalMeteorologyRepository;
        this.webScrap = webScrap;
    }

    public MedicalMeteorology persistDailyMedicalMeteorology() {
        MedicalMeteorology actualDailyMedicalReport = findActualDailyMedicalReport();
        if (actualDailyMedicalReport == null) {
            String data = webScrap.getMedicalMeteorologyDataFromUrl();
            LocalDateTime now = LocalDateTime.now();
            ZonedDateTime zonedUTC = now.atZone(ZoneId.of("GMT"));
            MedicalMeteorology medicalMeteorology = new MedicalMeteorology(zonedUTC, data);
            return medicalMeteorologyRepository.save(medicalMeteorology);
        }
        return actualDailyMedicalReport;
    }

    public MedicalMeteorology findActualDailyMedicalReport() {
        return medicalMeteorologyRepository.findByDate();
    }
}
