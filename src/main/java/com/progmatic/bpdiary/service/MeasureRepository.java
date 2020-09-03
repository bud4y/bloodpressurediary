package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.measurement.MeasurementDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<MeasurementDetails, Long> {
    
}
