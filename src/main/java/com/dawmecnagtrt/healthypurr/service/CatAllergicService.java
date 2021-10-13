package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.CatAllergic.CatAllergicDto;
import com.dawmecnagtrt.healthypurr.entity.CatAllergic;

import java.util.List;

public interface CatAllergicService {
    List<CatAllergicDto> getAll();
    List<CatAllergicDto> getAllByAllergicId(Integer allergicId);
    List<CatAllergicDto> getAllByCatId(Integer catId);
    CatAllergicDto getByCatIdAndAllergicId(Integer catId, Integer allergicId);
    CatAllergicDto createCatAllergic(Integer catId, Integer allergicId);
}
