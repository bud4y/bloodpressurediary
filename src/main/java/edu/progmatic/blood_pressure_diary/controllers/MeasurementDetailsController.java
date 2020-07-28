package edu.progmatic.blood_pressure_diary.controllers;

import edu.progmatic.blood_pressure_diary.dtos.MeasureDTO;
import edu.progmatic.blood_pressure_diary.models.evaluation.*;
import edu.progmatic.blood_pressure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_pressure_diary.services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private EvaluateService evaluateService;
    private WeatherService weatherService;
    private ConditionService conditionService;

    @Autowired
    public MeasurementDetailsController(MeasureService measureService, MedicalMeteorologyService medicalMeteorologyService,
                                        EvaluateService evaluateService, WeatherService weatherService, ConditionService conditionService) {
        this.measureService = measureService;
        this.medicalMeteorologyService = medicalMeteorologyService;
        this.evaluateService = evaluateService;
        this.weatherService = weatherService;
        this.conditionService = conditionService;
    }


    @PostMapping("/measure_details")
    public ResponseEntity<Map<String, Object>> responseForMeasureDetails(@RequestBody MeasureDTO measureDTO) {
        Map<String, Object> result = new HashMap<>();

        MedicalMeteorology actualDailyMedicalReport = medicalMeteorologyService.persistDailyMedicalMeteorology();
        Evaluate evaluate = evaluateService.evaluateMeasurement(measureDTO);
        WeatherData weatherData = weatherService.persistDailyWeatherData();
        Condition conditionById = conditionService.getConditionById(measureDTO.getConditionId());

        MeasurementDetails measurementDetails = measureService.persistNewMeasure(measureDTO, actualDailyMedicalReport, evaluate, weatherData, conditionById);
        BloodPressureValue optimalBloodPressure = evaluateService.getOptimalValueByAge(measurementDetails);

        result.put("medicalMeteorology", actualDailyMedicalReport);
        result.put("optimalBloodPressureValue", optimalBloodPressure);
        result.put("evaluateMeasurement", evaluate);
        result.put("weatherData", weatherData);

        return ResponseEntity.ok(result);
    }
}
