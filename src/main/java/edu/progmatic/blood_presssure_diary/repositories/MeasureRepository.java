package edu.progmatic.blood_presssure_diary.repositories;

import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<MeasurementDetails, Long> {

}
