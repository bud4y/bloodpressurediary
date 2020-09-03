package com.progmatic.bpdiary.web.controller;

import com.progmatic.bpdiary.model.evaluation.Condition;
import com.progmatic.bpdiary.service.impl.ConditionService;
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
