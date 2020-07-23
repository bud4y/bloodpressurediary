package edu.progmatic.blood_presssure_diary.repositories;

import edu.progmatic.blood_presssure_diary.models.measurement.MeasurementDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MeasureRepository extends JpaRepository<MeasurementDetails, Long> {

    @Query("SElECT measureDetails FROM  MeasurementDetails measureDetails WHERE measureDetails.user.id=:id")
    List<MeasurementDetails> findMeasurementDetailsById(@Param(value = "id") Long id);
}
