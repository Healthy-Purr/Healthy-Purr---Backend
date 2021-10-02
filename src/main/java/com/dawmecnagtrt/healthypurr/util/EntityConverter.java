package com.dawmecnagtrt.healthypurr.util;

import com.dawmecnagtrt.healthypurr.dto.User.CreateUserDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserFullDataDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserInfoDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserSimpleDto;
import com.dawmecnagtrt.healthypurr.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityConverter {
    @Autowired
    private ModelMapper modelMapper;

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
        return modelMapper.map(entity,UserSimpleDto.class);
    }


    //TODO: fill with other entities
}
