package edu.progmatic.blood_presssure_diary.services;

import edu.progmatic.blood_presssure_diary.models.evaluation.MedicalMeteorology;
import edu.progmatic.blood_presssure_diary.repositories.MedicalMeteorologyRepository;
import edu.progmatic.blood_presssure_diary.webscrap.WebScrap;
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
        String data = webScrap.getMedicalMeteorologyDataFromUrl();
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedUTC = now.atZone(ZoneId.of("GMT"));
        MedicalMeteorology medicalMeteorology = new MedicalMeteorology(zonedUTC, data);
        return medicalMeteorologyRepository.save(medicalMeteorology);
    }

    public MedicalMeteorology findActualDailyMedicalReport() {
        return medicalMeteorologyRepository.findByDate();
    }
}
