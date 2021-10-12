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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityConverter converter;

    @Override
    @Transactional(readOnly = true)
    public List<UserSimpleDto> getAll() {
        return converter.convertEntityToUserSimpleDto(userRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserEntity(Integer id){
        return userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User with id: " + id +" not found"));
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
    public UserFullDataDto createUser(CreateUserDto dto) throws Exception {
        if(userRepository.existsByUsername(dto.getUsername())){
            throw new Exception("Username already in use");
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .lastName(dto.getLastName())
                .status(1) // TODO: Define status values
                .build();
        UserFullDataDto dtoReturn = converter.convertEntityToUserFullDataDto(userRepository.save(user));
        userRepository.assignRole(dtoReturn.getUserId(),2);
        return dtoReturn;
    }

    @Override
    @Transactional
    public UserFullDataDto updateUserInfo(CreateUserDto dto, Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("User with id: " + id +" not found");
        }
        if(userRepository.existsByUsername(dto.getUsername()) && !Objects.equals(user.get().getUsername(), dto.getUsername())){
            throw new Exception("Username already in use");
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
    public byte[] updateUserPicture(MultipartFile picture, Integer id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("User with id: " + id +" not found");
        }
        User userUpdated = user.get();
        userUpdated.setUserPic(picture.getBytes());
        userUpdated.setStatus(2);
        return  userRepository.save(userUpdated).getUserPic();
    }

    @Override
    @Transactional
    public String deleteUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("User with id: " + id +" not found");
        }
        User userDeleted = user.get();
        userDeleted.setStatus(3);
        return "User with id: " + id +" deleted";
    }

    @Override
    @Transactional
    public void assignRole(Integer userId, Integer roleId) {
        userRepository.assignRole(userId, roleId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(()-> new EntityNotFoundException("User with username: " + username +" not found"));
        List<GrantedAuthority> authorities = user.getUserRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());
        System.out.println(authorities);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),true,true,true,true,authorities);
    }
}
