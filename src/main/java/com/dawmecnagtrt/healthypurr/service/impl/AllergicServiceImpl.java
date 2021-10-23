package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.CatProblem.CreateProblemDto;
import com.dawmecnagtrt.healthypurr.entity.Allergic;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.AllergicRepository;
import com.dawmecnagtrt.healthypurr.service.AllergicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllergicServiceImpl implements AllergicService {

    @Autowired
    private AllergicRepository allergicRepository;

    @Override
    public List<Allergic> getAllAllergic() {
        return allergicRepository.findAll();
    }

    @Override
    public Allergic getAllergic(Integer id) {
        return allergicRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Allergic with id: " + id +" not found"));
    }

    @Override
    public Allergic createAllergic(CreateProblemDto dto) throws Exception {
        Allergic allergic = Allergic.builder().description(dto.getDescription()).build();
        return allergicRepository.save(allergic);
    }

    @Override
    public Allergic updateAllergic(CreateProblemDto dto, Integer id) throws Exception {
        Optional<Allergic> allergic = allergicRepository.findById(id);
        if(!allergic.isPresent()){
            throw new EntityNotFoundException("Allergic with id: " + id +" not found");
        }
        Allergic allergicUpdated = allergic.get();
        allergicUpdated.setDescription(dto.getDescription());
        return allergicRepository.save(allergicUpdated);
    }

    @Override
    public String deleteAllergic(Integer id) {
        return null;
    }
}
