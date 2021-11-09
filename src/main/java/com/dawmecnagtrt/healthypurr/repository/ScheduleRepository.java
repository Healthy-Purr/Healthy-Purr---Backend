package com.dawmecnagtrt.healthypurr.repository;

import com.dawmecnagtrt.healthypurr.entity.Schedule;
import com.dawmecnagtrt.healthypurr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    Optional<Schedule> findScheduleByCatCatId(Integer catId);
}
