package com.progmatic.bpdiary.service;

import com.progmatic.bpdiary.model.evaluation.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConditionRepository extends JpaRepository<Condition, Long> {
    @Query("SElECT condition FROM  Condition condition WHERE condition.id=:id")
    Condition findConditionById(@Param(value = "id")Long id);
}
