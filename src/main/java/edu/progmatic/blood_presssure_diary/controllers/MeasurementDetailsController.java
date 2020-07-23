package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.dtos.MeasureDTO;
import edu.progmatic.blood_presssure_diary.models.evaluation.BloodPressureValue;
import edu.progmatic.blood_presssure_diary.models.evaluation.MedicalMeteorology;
import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_presssure_diary.services.MeasureService;
import edu.progmatic.blood_presssure_diary.services.MedicalMeteorologyService;
import edu.progmatic.blood_presssure_diary.services.PDFGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MeasurementDetailsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private MeasureService measureService;
    private MedicalMeteorologyService medicalMeteorologyService;
    private PDFGeneratorService pdfGeneratorService;

    @Autowired
    public MeasurementDetailsController(MeasureService measureService, MedicalMeteorologyService medicalMeteorologyService,
                                        PDFGeneratorService pdfGeneratorService) {
        this.measureService = measureService;
        this.medicalMeteorologyService = medicalMeteorologyService;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PostMapping("/measurementDetails")
    public ResponseEntity<Map<String, Object>> createNewMeasurement(@RequestBody MeasureDTO measureDTO) {
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

    @GetMapping("/user/getPDF")
    public ResponseEntity<?> getPDF() {
        return new ResponseEntity<>(pdfGeneratorService.generatePDF(), HttpStatus.OK);
    }
}
