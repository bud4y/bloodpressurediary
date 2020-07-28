package edu.progmatic.blood_pressure_diary.controllers;

import edu.progmatic.blood_pressure_diary.models.evaluation.Condition;
import edu.progmatic.blood_pressure_diary.services.ConditionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConditionController {
    private ConditionService conditionService;

    public ConditionController(ConditionService conditionService){
        this.conditionService = conditionService;
    }

    @GetMapping("/conditions")
    public List<Condition> getConditions(){
       return conditionService.getAllConditions();
    }
}
