package edu.progmatic.blood_presssure_diary.repositories;

import edu.progmatic.blood_presssure_diary.models.evaluation.MedicalMeteorology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface MedicalMeteorologyRepository extends JpaRepository <MedicalMeteorology, Long>{
   // @Query("SELECT mm FROM MedicalMeteorology mm WHERE mm.date = :currentDate()")
    @Query(value = "SELECT text FROM medical_meteorology WHERE date = current_date", nativeQuery = true)
    MedicalMeteorology findByDate();
}
