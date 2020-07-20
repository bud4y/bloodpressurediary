package edu.progmatic.blood_presssure_diary.controllers;

import edu.progmatic.blood_presssure_diary.dtos.MeasureDTO;
import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_presssure_diary.services.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasurementDetailsController {
    MeasureService measureService;

    @Autowired
    public MeasurementDetailsController(MeasureService measureService){
        this.measureService = measureService;
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @PostMapping("measure_details")
    public ResponseEntity<?> getMeasureDetails(@RequestBody MeasureDTO measureDTO){
        MeasurementDetails measurementDetails = measureService.persistNewMeasure(measureDTO);
        return new ResponseEntity<>(measurementDetails, HttpStatus.OK);
    }
}
