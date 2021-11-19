package com.dawmecnagtrt.healthypurr.repository;

import com.dawmecnagtrt.healthypurr.entity.EvaluationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationResultRepository extends JpaRepository<EvaluationResult, Integer> {
    List<EvaluationResult> findAllByUserUserId(Integer userId);
    List<EvaluationResult> findAllByCatCatId(Integer catId);
    List<EvaluationResult> findAllByEvaluatedFoodEvaluatedFoodId(Integer evFoodId);
}
