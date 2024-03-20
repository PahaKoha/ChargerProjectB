package com.backend.PowerUp.controllers;

import com.backend.PowerUp.entities.UserImage;
import com.backend.PowerUp.services.UserImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ImageDataController {
    private final UserImageService userImageService;

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
//        userImageService.uploadUserImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(file);
    }

//    @GetMapping("/image/info/{name}")
//    public ResponseEntity<?>  getImageInfoByName(@PathVariable("name") String name){
//        UserImage image = userImageService.getInfoByImageByName(name);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(image);
//    }
//
//    @GetMapping("/image/{name}")
//    public ResponseEntity<?>  getImageByName(@PathVariable("name") String name){
//        byte[] image = userImageService.getImage(name);
//
//        return ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(image);
//    }


}
