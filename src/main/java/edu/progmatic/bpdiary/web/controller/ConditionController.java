package edu.progmatic.bpdiary.web.controller;

import edu.progmatic.bpdiary.model.evaluation.Condition;
import edu.progmatic.bpdiary.service.impl.ConditionService;
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
