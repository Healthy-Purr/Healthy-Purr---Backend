package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.User.*;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public ApiResponse<List<UserSimpleDto>> getAllUsers(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            userService.getAll()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/full-data")
    ApiResponse<UserFullDataDto> getUserFullDataById(@PathVariable Integer userId) throws EntityNotFoundException{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                userService.getUserFullDataById(userId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/info")
    ApiResponse<UserInfoDto> getUserInfoById(@PathVariable Integer userId) throws EntityNotFoundException{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                userService.getUserInfoById(userId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/simple")
    ApiResponse<UserSimpleDto> getUserSimpleById(@PathVariable Integer userId) throws EntityNotFoundException{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                userService.getUserSimpleById(userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    ApiResponse<UserFullDataDto> createUser(@RequestBody @Valid CreateUserDto dto) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                userService.createUser(dto));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}/picture")
    ApiResponse<UserSimpleDto> updateUserPicture(@PathVariable Integer userId ,@RequestParam MultipartFile file) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                userService.updateUserPicture(file, userId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/picture")
    public ResponseEntity<?> getFoto(@PathVariable Integer userId)  {
        User optional = userService.getUser(userId);
        Resource imagen = new ByteArrayResource(optional.getUserPic());
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }
    //TODO: Add endpoints
}
