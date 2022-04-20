package com.dawmecnagtrt.healthypurr.repository;

import com.dawmecnagtrt.healthypurr.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findScheduleByCatCatId(Integer catId);
}
