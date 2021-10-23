package com.dawmecnagtrt.healthypurr.util;

import com.dawmecnagtrt.healthypurr.dto.Cat.CatDto;
import com.dawmecnagtrt.healthypurr.dto.Cat.CreateCatDto;
import com.dawmecnagtrt.healthypurr.dto.CatProblem.CatAllergicDto;
import com.dawmecnagtrt.healthypurr.dto.CatProblem.CatDiseaseDto;
import com.dawmecnagtrt.healthypurr.dto.User.CreateUserDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserFullDataDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserInfoDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserSimpleDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.entity.CatAllergic;
import com.dawmecnagtrt.healthypurr.entity.CatDisease;
import com.dawmecnagtrt.healthypurr.entity.User;
import org.modelmapper.Converter;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    Converter<byte[],Resource> imageTransformation = ctx -> ctx.getSource() == null ? null : TransformToImage(ctx.getSource());
    public Resource TransformToImage(byte[] pic){
        return new ByteArrayResource(pic);
    }

    // User
    public User convertCreateUserToEntity(CreateUserDto dto){
        return modelMapper.map(dto,User.class);
    }
    public UserFullDataDto convertEntityToUserFullDataDto(User entity){
        return modelMapper.map(entity,UserFullDataDto.class);
    }
    public UserInfoDto convertEntityToUserInfoDto(User entity){
        return modelMapper.map(entity,UserInfoDto.class);
    }
    public UserSimpleDto convertEntityToUserSimpleDto(User entity){
        //System.out.println(entity);
//        return modelMapper.typeMap(User.class,UserSimpleDto.class)
//                .addMappings(modelMapper -> modelMapper.using(imageTransformation).map(User::getUserPic, UserSimpleDto::setPhoto))
//                .map(entity);
        return modelMapper.map(entity,UserSimpleDto.class);
    }
    public List<UserSimpleDto> convertEntityToUserSimpleDto(List<User> users) {
        return users.stream()
                .map(this::convertEntityToUserSimpleDto)
                .collect(Collectors.toList());
    }

    // Cat
    public Cat convertCreateCatToEntity (CreateCatDto dto){ return modelMapper.map(dto,Cat.class);}
    public CatDto convertEntityToCatDto( Cat entity) { return modelMapper.map(entity,CatDto.class);}
    public List<CatDto> convertEntityToCatDto(List<Cat> cats) {
        return cats.stream()
                .map(this::convertEntityToCatDto)
                .collect(Collectors.toList());
    }

    //CatAllergic
    public CatAllergicDto convertEntityToCatAllergicDto(CatAllergic entity){ return  modelMapper.map(entity,CatAllergicDto.class);}
    public List<CatAllergicDto> convertEntityToCatAllergicDto(List<CatAllergic> catAllergics) {
        return catAllergics.stream()
                .map(this::convertEntityToCatAllergicDto)
                .collect(Collectors.toList());
    }
    //CatDisease
    public CatDiseaseDto convertEntityToCatDiseaseDto(CatDisease entity){ return  modelMapper.map(entity,CatDiseaseDto.class);}
    public List<CatDiseaseDto> convertEntityToCatDiseaseDto(List<CatDisease> catAllergics) {
        return catAllergics.stream()
                .map(this::convertEntityToCatDiseaseDto)
                .collect(Collectors.toList());
    }
}
