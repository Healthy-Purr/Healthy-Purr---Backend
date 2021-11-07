package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.Meal.CreateMealDto;
import com.dawmecnagtrt.healthypurr.dto.Meal.MealDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.Meal;
import com.dawmecnagtrt.healthypurr.entity.Schedule;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.MealRepository;
import com.dawmecnagtrt.healthypurr.repository.ScheduleRepository;
import com.dawmecnagtrt.healthypurr.service.MealService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    public List<MealDto> getAll() {
        return converter.convertEntityToMealDto(mealRepository.findAll());
    }

    @Override
    public MealDto getMeal(Integer id) {
        return converter.convertEntityToMealDto(mealRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Meal with id: " + id +" not found")));
    }

    @Override
    public List<MealDto> getAllByScheduleId(Integer scheduleId) {
        return converter.convertEntityToMealDto(mealRepository.findAllByScheduleScheduleId(scheduleId));
    }

    @Override
    public MealDto createMeal(CreateMealDto dto) throws Exception {
        Optional<Schedule> schedule = scheduleRepository.findById(dto.getScheduleId());
        if(!schedule.isPresent()){
            throw new EntityNotFoundException("Schedule with id: " + dto.getScheduleId() +" not found");
        }
        Schedule scheduleBD = schedule.get();
        String[] parts = dto.getHour().split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        String[] nanoparts = parts[2].split("\\.");
        int second = Integer.parseInt(nanoparts[0]);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
        Meal meal = Meal.builder()
                .hour(LocalTime.of(hour, minute, second))
                .quantity(dto.getQuantity())
                .dry(dto.getDry())
                .damp(dto.getDamp())
                .medicine(dto.getMedicine())
                .schedule(scheduleBD)
                .build();
        return converter.convertEntityToMealDto(mealRepository.save(meal));
    }

    @Override
    public MealDto updateMeal(CreateMealDto dto, Integer id) throws Exception {
        Optional<Meal> meal = mealRepository.findById(id);
        if(!meal.isPresent()){
            throw new EntityNotFoundException("Meal with id: " + id +" not found");
        }
        String[] parts = dto.getHour().split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        String[] nanoparts = parts[2].split("\\.");
        int second = Integer.parseInt(nanoparts[0]);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
        Meal mealUpdated = meal.get();
        mealUpdated.setHour(LocalTime.of(hour, minute, second));
        mealUpdated.setQuantity(dto.getQuantity());
        mealUpdated.setDry(dto.getDry());
        mealUpdated.setDamp(dto.getDamp());
        mealUpdated.setMedicine(dto.getMedicine());
        return converter.convertEntityToMealDto(mealRepository.save(mealUpdated));
    }
}
