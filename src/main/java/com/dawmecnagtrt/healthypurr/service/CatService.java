package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.Cat.CatDto;
import com.dawmecnagtrt.healthypurr.dto.Cat.CreateCatDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CatService {
    List<CatDto> getAll();
    CatDto getCat(Integer id);
    Cat getCatEntity(Integer id);
    List<CatDto> getAllByUserId(Integer userId);
    CatDto createCat(CreateCatDto dto, Integer userId) throws Exception;
    CatDto updateCat(CreateCatDto dto, Integer id) throws Exception;
    byte[] updateCatPicture (MultipartFile picture, Integer id) throws Exception;
    String deleteCat(Integer id);
}
