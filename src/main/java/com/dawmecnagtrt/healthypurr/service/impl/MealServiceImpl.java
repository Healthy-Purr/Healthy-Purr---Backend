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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<MealDto> getAll() {
        return converter.convertEntityToMealDto(mealRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public MealDto getMeal(Integer id) {
        return converter.convertEntityToMealDto(mealRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Meal with id: " + id +" not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public List<MealDto> getAllByScheduleId(Integer scheduleId) {
        return converter.convertEntityToMealDto(mealRepository.findAllByScheduleScheduleId(scheduleId));
    }

    @Override
    @Transactional
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
        int nanosecond = Integer.parseInt(nanoparts[1]);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
        System.out.println(nanosecond);
        Meal meal = Meal.builder()
                .description(dto.getDescription())
                .hour(LocalTime.of(hour, minute, second))
                .isDry(dto.getIsDry())
                .isDamp(dto.getIsDamp())
                .hasMedicine(dto.getHasMedicine())
                .schedule(scheduleBD)
                .status(true)
                .build();
        meal.setStatus(meal.getIsDry() || meal.getIsDamp() || meal.getHasMedicine());
        return converter.convertEntityToMealDto(mealRepository.save(meal));
    }

    @Override
    @Transactional
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
        int nanosecond = Integer.parseInt(nanoparts[1]);
        System.out.println(hour);
        System.out.println(minute);
        System.out.println(second);
        System.out.println(nanosecond);
        Meal mealUpdated = meal.get();
        mealUpdated.setHour(LocalTime.of(hour, minute, second));
        mealUpdated.setDescription(dto.getDescription());
        mealUpdated.setIsDry(dto.getIsDry());
        mealUpdated.setIsDamp(dto.getIsDamp());
        mealUpdated.setHasMedicine(dto.getHasMedicine());
        mealUpdated.setStatus(mealUpdated.getIsDry() || mealUpdated.getIsDamp() || mealUpdated.getHasMedicine());
        Meal mealBD = mealRepository.save(mealUpdated);
        Schedule schedule = mealBD.getSchedule();
        int flag = 0;
        for (Meal schMeal : schedule.getMeals()) {
            if (schMeal.getStatus()) {
                flag += 1;
            }
        }
        if(flag == 0){
            schedule.setStatus(false);
            scheduleRepository.save(schedule);
        }
        return getMeal(mealBD.getMealId());
    }
}
