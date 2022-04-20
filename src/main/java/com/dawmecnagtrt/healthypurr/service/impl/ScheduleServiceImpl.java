package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.Schedule.CreateScheduleDto;
import com.dawmecnagtrt.healthypurr.dto.Schedule.ScheduleDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.Schedule;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.CatRepository;
import com.dawmecnagtrt.healthypurr.repository.ScheduleRepository;
import com.dawmecnagtrt.healthypurr.service.ScheduleService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl  implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleDto> getAll() {
        return converter.convertEntityToScheduleDto(scheduleRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleDto getSchedule(Integer id) {
        return converter.convertEntityToScheduleDto(scheduleRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Schedule with id: " + id +" not found")));
    }

    @Override
    public List<ScheduleDto> getSchedulesByCatId(Integer catId) {
        return converter.convertEntityToScheduleDto(scheduleRepository.findScheduleByCatCatId(catId));
    }

    @Override
    @Transactional
    public ScheduleDto createSchedule(CreateScheduleDto dto) throws Exception {
        Optional<Cat> cat = catRepository.findById(dto.getCatId());
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + cat +" not found");
        }
        Cat catBD = cat.get();
        Schedule schedule = Schedule.builder()
                .cat(catBD)
                .day(dto.getDay())
                .status(false)
                .build();
        return converter.convertEntityToScheduleDto(scheduleRepository.save(schedule));
    }
}
