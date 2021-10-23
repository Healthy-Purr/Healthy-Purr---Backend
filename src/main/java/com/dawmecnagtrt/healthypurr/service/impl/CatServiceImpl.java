package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.Cat.CatDto;
import com.dawmecnagtrt.healthypurr.dto.Cat.CreateCatDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserFullDataDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.Role;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.CatRepository;
import com.dawmecnagtrt.healthypurr.repository.UserRepository;
import com.dawmecnagtrt.healthypurr.service.CatService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Optional;

@Service
public class CatServiceImpl implements CatService {

    @Autowired
    private CatRepository catRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<CatDto> getAll() {
        return converter.convertEntityToCatDto(catRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public CatDto getCat(Integer id) {
        return converter.convertEntityToCatDto(catRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cat with id: " + id +" not found")));
    }
    @Override
    @Transactional(readOnly = true)
    public Cat getCatEntity(Integer id) {
        return catRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Cat with id: " + id +" not found"));
    }
    @Override
    @Transactional(readOnly = true)
    public List<CatDto> getAllByUserId(Integer userId) {
        return converter.convertEntityToCatDto(catRepository.findAllByUserUserId(userId));
    }

    @Override
    @Transactional
    public CatDto createCat(CreateCatDto dto, Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            throw new EntityNotFoundException("User with id: " + userId +" not found");
        }
        User userBD = user.get();
        Cat cat = Cat.builder()
                .name(dto.getName())
                .weight(dto.getWeight())
                .age(dto.getAge())
                .hasDisease(dto.getHasDisease())
                .isAllergic(dto.getIsAllergic())
                .user(userBD)
                .status(1)
                .build();
        return converter.convertEntityToCatDto(catRepository.save(cat));
    }

    @Override
    @Transactional
    public CatDto updateCat(CreateCatDto dto, Integer id) throws Exception {
        Optional<Cat> cat = catRepository.findById(id);
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + id +" not found");
        }
        Cat catUpdated = cat.get();
        catUpdated.setName(dto.getName());
        catUpdated.setWeight(dto.getWeight());
        catUpdated.setAge(dto.getAge());
        catUpdated.setHasDisease(dto.getHasDisease());
        catUpdated.setIsAllergic(dto.getIsAllergic());
        catUpdated.setStatus(2);
        return converter.convertEntityToCatDto(catRepository.save(catUpdated));
    }

    @Override
    @Transactional
    public byte[] updateCatPicture(MultipartFile picture, Integer id) throws Exception {
        Optional<Cat> cat = catRepository.findById(id);
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + id +" not found");
        }
        Cat catUpdated = cat.get();
        catUpdated.setCatPic(picture.getBytes());
        catUpdated.setStatus(2);
        return  catRepository.save(catUpdated).getCatPic();
    }

    @Override
    @Transactional
    public String deleteCat(Integer id) {
        Optional<Cat> cat = catRepository.findById(id);
        if(!cat.isPresent()){
            throw new EntityNotFoundException("Cat with id: " + id +" not found");
        }
        Cat catDeleted = cat.get();
        catDeleted.setStatus(3);
        return "Cat with id: " + id +" deleted";
    }
}
