package com.dawmecnagtrt.healthypurr.service.impl;

import com.dawmecnagtrt.healthypurr.dto.User.CreateUserDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserFullDataDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserInfoDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserSimpleDto;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.repository.UserRepository;
import com.dawmecnagtrt.healthypurr.service.UserService;
import com.dawmecnagtrt.healthypurr.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public UserFullDataDto getUserFullDataById(Integer id) {
        return converter.convertEntityToUserFullDataDto(userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User with id: " + id +" not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public UserInfoDto getUserInfoById(Integer id) {
        return converter.convertEntityToUserInfoDto(userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User with id: " + id +" not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public UserSimpleDto getUserSimpleById(Integer id) {
        return converter.convertEntityToUserSimpleDto(userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User with id: " + id +" not found")));
    }

    @Override
    @Transactional(readOnly = true)
    public UserFullDataDto getUserFullDataByUsername(String username) {
        return converter.convertEntityToUserFullDataDto(userRepository.findUserByUsername(username)
                .orElseThrow(()-> new EntityNotFoundException("User with username: " + username +" not found")));
    }

    @Override
    @Transactional
    public UserFullDataDto createUser(CreateUserDto dto, MultipartFile picture) throws Exception {
        if(!userRepository.existsByUsername(dto.getUsername())){
            throw new Exception("Username already in use");
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .userPic(picture.getBytes())
                .status(1) // TODO: Define status values
                .build();
        return converter.convertEntityToUserFullDataDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserFullDataDto updateUserInfo(CreateUserDto dto, Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("User with id: " + id +" not found");
        }
        User userUpdated = user.get();
        userUpdated.setUsername(dto.getUsername());
        userUpdated.setPassword(dto.getPassword());
        userUpdated.setName(dto.getName());
        userUpdated.setLastName(dto.getLastName());
        userUpdated.setStatus(2);
        return converter.convertEntityToUserFullDataDto(userRepository.save(userUpdated));
    }

    @Override
    @Transactional
    public UserSimpleDto updateUserPicture(MultipartFile picture, Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("User with id: " + id +" not found");
        }
        User userUpdated = user.get();
        userUpdated.setUserPic(picture.getBytes());
        userUpdated.setStatus(2);
        return converter.convertEntityToUserSimpleDto(userRepository.save(userUpdated));
    }

    @Override
    @Transactional
    public String deleteUser(Integer id) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException("User with id: " + id +" not found");
        }
        userRepository.deleteById(id);
        return "User with id: " + id +" deleted";
    }
}
