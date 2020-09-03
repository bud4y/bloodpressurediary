package com.progmatic.bpdiary.service.impl;

import com.progmatic.bpdiary.model.evaluation.Condition;
import com.progmatic.bpdiary.service.ConditionRepository;
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
