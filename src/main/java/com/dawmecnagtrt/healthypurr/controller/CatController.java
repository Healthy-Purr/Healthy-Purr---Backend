package com.dawmecnagtrt.healthypurr.controller;

import com.dawmecnagtrt.healthypurr.dto.Cat.CatDto;
import com.dawmecnagtrt.healthypurr.dto.Cat.CreateCatDto;
import com.dawmecnagtrt.healthypurr.dto.User.CreateUserDto;
import com.dawmecnagtrt.healthypurr.dto.User.UserFullDataDto;
import com.dawmecnagtrt.healthypurr.entity.Cat;
import com.dawmecnagtrt.healthypurr.exception.EntityNotFoundException;
import com.dawmecnagtrt.healthypurr.response.ApiResponse;
import com.dawmecnagtrt.healthypurr.service.CatService;
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
@RequestMapping
public class CatController {
    @Autowired
    CatService catService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cats")
    public ApiResponse<List<CatDto>> getAllCats(){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            catService.getAll()); }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("cats/{catId}")
    public ApiResponse<CatDto> getCatById(@PathVariable Integer catId) throws EntityNotFoundException {
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catService.getCat(catId));
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}/cats")
    public ApiResponse<List<CatDto>> getAllCatsByUserId(@PathVariable Integer userId){return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
            catService.getAllByUserId(userId)); }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cats/{catId}/picture")
    public ResponseEntity<?> getFoto(@PathVariable Integer catId)  {
        Cat optional = catService.getCatEntity(catId);
        if(optional.getCatPic() != null){
            Resource imagen = new ByteArrayResource(optional.getCatPic());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
        }
        else{
            return ResponseEntity.ok().body("Cat does not have picture");
        }
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cats/{catId}/picture")
    public ResponseEntity<?> updateCatPicture(@PathVariable Integer catId ,@RequestParam MultipartFile file) throws Exception {
        Resource imagen = new ByteArrayResource(catService.updateCatPicture(file, catId));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/users/{userId}/cats")
    public ApiResponse<CatDto> createCat(@PathVariable Integer userId, @RequestBody @Valid CreateCatDto dto) throws Exception {
        return new ApiResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                catService.createCat(dto, userId));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cats/{catId}")
    public ApiResponse<CatDto> UpdateCatById(@RequestBody @Valid CreateCatDto dto, @PathVariable Integer catId) throws Exception{
        return new ApiResponse<>("Success", String.valueOf(HttpStatus.OK),"OK",
                catService.updateCat(dto,catId));
    }
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/cats/{catId}/delete")
    public String deleteCat(@PathVariable Integer catId){
        return catService.deleteCat(catId);
    }
}
