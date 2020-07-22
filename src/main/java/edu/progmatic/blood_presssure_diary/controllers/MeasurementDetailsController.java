package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.dtos.MeasureDTO;
import edu.progmatic.blood_presssure_diary.models.evaluation.BloodPressureValue;
import edu.progmatic.blood_presssure_diary.models.evaluation.MedicalMeteorology;
import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_presssure_diary.services.MeasureService;
import edu.progmatic.blood_presssure_diary.services.MedicalMeteorologyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MeasurementDetailsController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private MeasureService measureService;
    private MedicalMeteorologyService medicalMeteorologyService;

    @Autowired
    public MeasurementDetailsController(MeasureService measureService, MedicalMeteorologyService medicalMeteorologyService) {
        this.measureService = measureService;
        this.medicalMeteorologyService = medicalMeteorologyService;
    }

    // @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("measure_details")
    public ResponseEntity<Map<String, Object>> responseForMeasureDetails(@RequestBody MeasureDTO measureDTO) {
        Map<String, Object> result = new HashMap<String, Object>();
        MeasurementDetails measurementDetails = measureService.persistNewMeasure(measureDTO);

        BloodPressureValue bloodPressureValue = measureService.evaluateMeasurement(measurementDetails);

        if (medicalMeteorologyService.findActualDailyMedicalReport() == null) {
            MedicalMeteorology medicalMeteorology = medicalMeteorologyService.persistDailyMedicalMeteorology();
            result.put("medicalMeteorology", medicalMeteorology);
            result.put("optimalBloodPressureValue", bloodPressureValue);
        }
        return ResponseEntity.ok(result);
    }
}
