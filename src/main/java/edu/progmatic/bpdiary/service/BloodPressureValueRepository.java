package edu.progmatic.bpdiary.service;

import edu.progmatic.bpdiary.model.evaluation.BloodPressureValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BloodPressureValueRepository extends JpaRepository<BloodPressureValue, Long> {

    // @Query(value = "SELECT min_value, max_value, proper_value FROM blood_pressure_value WHERE min_age <= ?1 AND max_age >= ?1", nativeQuery = true)
    @Query("SELECT bp FROM BloodPressureValue bp WHERE bp.minAge <= :age AND bp.maxAge >= :age")
    BloodPressureValue findByAge(@Param("age") Integer age);
}
