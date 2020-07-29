package edu.progmatic.blood_pressure_diary.services;

import edu.progmatic.blood_pressure_diary.dtos.MeasureDTO;
import edu.progmatic.blood_pressure_diary.models.evaluation.BloodPressureValue;
import edu.progmatic.blood_pressure_diary.models.evaluation.Evaluate;
import edu.progmatic.blood_pressure_diary.models.measurement.MeasurementDetails;
import edu.progmatic.blood_pressure_diary.models.registration.User;
import edu.progmatic.blood_pressure_diary.repositories.BloodPressureValueRepository;
import edu.progmatic.blood_pressure_diary.repositories.EvaluateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EvaluateService {
    private  BloodPressureValueRepository bloodPressureValueRepository;
    private  EvaluateRepository evaluateRepository;

    @Autowired
    public EvaluateService(BloodPressureValueRepository bloodPressureValueRepository, EvaluateRepository evaluateRepository) {
        this.bloodPressureValueRepository = bloodPressureValueRepository;
        this.evaluateRepository = evaluateRepository;
    }

    public BloodPressureValue getOptimalValueByAge(MeasurementDetails measurementDetails) {
        User user = measurementDetails.getUser();
        int userAge = Math.abs(user.getBirthDate().getYear() - LocalDate.now().getYear());

        return bloodPressureValueRepository.findByAge(userAge);
    }

    public Evaluate evaluateMeasurement(MeasureDTO measurementDetails) {
        int systolicValue = measurementDetails.getSystolic();

        if (systolicValue < 100) {
            return evaluateRepository.findLowBloodPressure();
        }
        if (systolicValue > 100 && systolicValue <= 120) {
            return evaluateRepository.findOptimalBloodPressure();
        }
        if (systolicValue > 120 && systolicValue <= 130) {
            return evaluateRepository.findNormalBloodPressure();
        }
        if (systolicValue > 130 && systolicValue <= 140) {
            return evaluateRepository.findHypertensionStageOne();
        }
        if (systolicValue > 140 && systolicValue <= 180) {
            return evaluateRepository.findHypertensionStageTwo();
        }
        else {
            return evaluateRepository.findHypertensionStageThree();
        }
    }
}
