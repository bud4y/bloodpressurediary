package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.evaluation.MedicalMeteorology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicalMeteorologyRepository extends JpaRepository <MedicalMeteorology, Long>{
    // @Query("SELECT mm FROM MedicalMeteorology mm WHERE mm.date = :currentDate()")
    @Query(value = "SELECT * FROM medical_meteorology WHERE DATE(date) = DATE(NOW())", nativeQuery = true)
    MedicalMeteorology findByDate();
}
