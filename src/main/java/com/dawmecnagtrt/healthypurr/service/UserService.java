package com.dawmecnagtrt.healthypurr.service;

import com.dawmecnagtrt.healthypurr.dto.User.*;
import com.dawmecnagtrt.healthypurr.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UserSimpleDto> getAll();
    User getUser(Integer userId);
    UserFullDataDto getUserFullDataById(Integer id);
    UserInfoDto getUserInfoById(Integer id);
    UserSimpleDto getUserSimpleById(Integer id);
    UserFullDataDto getUserFullDataByUsername(String username);
    UserFullDataDto createUser(CreateUserDto dto) throws Exception;
    UserFullDataDto updateUserInfo(CreateUserDto dto, Integer id) throws Exception;
    byte[] updateUserPicture (MultipartFile picture, Integer id) throws Exception;
    String deleteUser(Integer id);
    void assignRole(Integer userId, Integer roleId);
}
