package edu.progmatic.blood_pressure_diary.services;

import edu.progmatic.blood_pressure_diary.models.evaluation.Condition;
import edu.progmatic.blood_pressure_diary.repositories.ConditionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConditionService {
    private ConditionRepository conditionRepository;

    public ConditionService(ConditionRepository conditionRepository){
        this.conditionRepository = conditionRepository;
    }

    public List<Condition> getAllConditions(){
        return conditionRepository.findAll();
    }

    public Condition getConditionById(Long id) {
        return conditionRepository.findConditionById(id);
    }
}
