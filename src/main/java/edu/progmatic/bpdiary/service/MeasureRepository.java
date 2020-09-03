package edu.progmatic.bpdiary.service;

import edu.progmatic.bpdiary.model.measurement.MeasurementDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<MeasurementDetails, Long> {
    
}
