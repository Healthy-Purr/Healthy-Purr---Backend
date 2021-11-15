package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.CatProblem.CatAllergicDto;
import com.dawmecnagtrt.healthypurr.entity.Allergic;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.CatAllergic;
import com.dawmecnagtrt.healthypurr.entity.CatDisease;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.AllergicRepository;
import com.dawmecnagtrt.healthypurr.repository.CatAllergicRepository;
import com.dawmecnagtrt.healthypurr.repository.CatRepository;
import com.dawmecnagtrt.healthypurr.service.CatAllergicService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatAllergicServiceImpl implements CatAllergicService {

    @Autowired
    private CatAllergicRepository catAllergicRepository;

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private AllergicRepository allergicRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    public List<CatAllergicDto> getAll() {
        return converter.convertEntityToCatAllergicDto(catAllergicRepository.findAll());
    }

    @Override
    public List<CatAllergicDto> getAllByAllergicId(Integer allergicId) {
        return converter.convertEntityToCatAllergicDto(catAllergicRepository.findAllByAllergicAllergicId(allergicId));
    }

    @Override
    public List<CatAllergicDto> getAllByCatId(Integer catId) {
        return converter.convertEntityToCatAllergicDto(catAllergicRepository.findAllByCatCatId(catId));
    }

    @Override
    public CatAllergicDto getByCatIdAndAllergicId(Integer catId, Integer allergicId) {
        return converter.convertEntityToCatAllergicDto(catAllergicRepository.findCatAllergic(catId,allergicId)
                .orElseThrow(()-> new EntityNotFoundException("Cat-Allergic with catId: " + catId + " and allergicId:"+ allergicId +"not found")));
    }

    @Override
    public CatAllergicDto createCatAllergic(Integer catId, Integer allergicId) {
        Optional<Cat> cat = catRepository.findById(catId);
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + catId +" not found");
        }
        Cat catBD = cat.get();
        Optional<Allergic> allergic = allergicRepository.findById(allergicId);
        if(!allergic.isPresent()){
            throw new EntityNotFoundException("Allergic with id: " + allergicId +" not found");
        }
        Allergic allergicBD = allergic.get();
        CatAllergic catAllergic = CatAllergic.builder()
                .cat(catBD)
                .allergic(allergicBD)
                .allergicId(allergicId)
                .catId(catId)
                .status(true)
                .build();
        return converter.convertEntityToCatAllergicDto(catAllergicRepository.save(catAllergic));
    }

    @Override
    public CatAllergicDto updateCatAllergic(Integer catId, Integer allergicId, Boolean status) {
        CatAllergic catAllergic = catAllergicRepository.findCatAllergic(catId,allergicId)
                .orElseThrow(()-> new EntityNotFoundException("Cat-Allergic with catId: " + catId + " and allergicId:"+ allergicId +"not found"));
        catAllergic.setStatus(status);
        return converter.convertEntityToCatAllergicDto(catAllergicRepository.save(catAllergic));
    }
}
