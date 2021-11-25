package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.User.*;
import com.dawmecnagtrt.healthypurr.entity.User;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @SecurityRequirement(name = "usePurrApi")
    public ApiResponse<List<UserSimpleDto>> getAllUsers(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            userService.getAll()); }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/full-data")
    @SecurityRequirement(name = "usePurrApi")
    public ApiResponse<UserFullDataDto> getUserFullDataById(@PathVariable Integer userId) throws EntityNotFoundException{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                userService.getUserFullDataById(userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/info")
    @SecurityRequirement(name = "usePurrApi")
    public ApiResponse<UserInfoDto> getUserInfoById(@PathVariable Integer userId) throws EntityNotFoundException{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                userService.getUserInfoById(userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/simple")
    @SecurityRequirement(name = "usePurrApi")
    public ApiResponse<UserSimpleDto> getUserSimpleById(@PathVariable Integer userId) throws EntityNotFoundException{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                userService.getUserSimpleById(userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public ApiResponse<UserFullDataDto> createUser(@RequestBody @Valid CreateUserDto dto) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                userService.createUser(dto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}")
    @SecurityRequirement(name = "usePurrApi")
    public ApiResponse<UserFullDataDto> UpdateUserById(@RequestBody @Valid CreateUserDto dto, @PathVariable Integer userId) throws Exception{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                userService.updateUserInfo(dto,userId));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}/picture")
    @SecurityRequirement(name = "usePurrApi")
    public ResponseEntity<?> updateUserPicture(@PathVariable Integer userId ,@RequestParam MultipartFile file) throws Exception {
        Resource imagen = new ByteArrayResource(userService.updateUserPicture(file, userId));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/picture")
    @SecurityRequirement(name = "usePurrApi")
    public ResponseEntity<?> getFoto(@PathVariable Integer userId)  {
        User optional = userService.getUserEntity(userId);
        if(optional.getUserPic() != null){
            Resource imagen = new ByteArrayResource(optional.getUserPic());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }
        else{
           return ResponseEntity.ok().body("User does not have picture");
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{userId}/delete")
    @SecurityRequirement(name = "usePurrApi")
    public String deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }

    @PostMapping("/{userId}")
    @SecurityRequirement(name = "usePurrApi")
    public ResponseEntity<String> assignRole(@PathVariable("userId") Integer userId, @RequestParam("roleId") Integer roleId) {
        userService.assignRole(userId, roleId);
        return  ResponseEntity.ok("Assigned");
    }
}
