package com.dawmecnagtrt.healthypurr.repository;

import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.Meal;
import com.dawmecnagtrt.healthypurr.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealRepository extends JpaRepository<Meal,Integer> {
    List<Meal> findAllByScheduleScheduleId(Integer scheduleId);
}
