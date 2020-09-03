package edu.progmatic.bpdiary.service;

import edu.progmatic.bpdiary.model.evaluation.Evaluate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EvaluateRepository extends JpaRepository<Evaluate, Long> {
    @Query(value = "SELECT * FROM evaluate WHERE text = 'Alacsony'", nativeQuery = true)
    Evaluate findLowBloodPressure();
    @Query(value = "SELECT * FROM evaluate WHERE text = 'Optimális'", nativeQuery = true)
    Evaluate findOptimalBloodPressure();
    @Query(value = "SELECT * FROM evaluate WHERE text = 'Normális'", nativeQuery = true)
    Evaluate findNormalBloodPressure();
    @Query(value = "SELECT * FROM evaluate WHERE text = 'Emelkedett'", nativeQuery = true)
    Evaluate findPreHighBloodPressure();
    @Query(value = "SELECT * FROM evaluate WHERE text = 'Elsőfokú'", nativeQuery = true)
    Evaluate findHypertensionStageOne();
    @Query(value = "SELECT * FROM evaluate WHERE text = 'Másodfokú'", nativeQuery = true)
    Evaluate findHypertensionStageTwo();
    @Query(value = "SELECT * FROM evaluate WHERE text = 'Harmadfokú'", nativeQuery = true)
    Evaluate findHypertensionStageThree();

}
