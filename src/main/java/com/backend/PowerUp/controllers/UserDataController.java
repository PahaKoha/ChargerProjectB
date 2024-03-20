package com.backend.PowerUp.controllers;

import com.backend.PowerUp.dto.MainUserData;
import com.backend.PowerUp.entities.UserImage;
import com.backend.PowerUp.services.UserService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserDataController {
    private final UserService userService;

    @PostMapping("/changeUserData")
    public ResponseEntity<?> changeUserData(@RequestParam("image") MultipartFile file, @RequestParam("json") String json, Principal principal) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MainUserData userData = objectMapper.readValue(json, MainUserData.class);
        System.out.println(principal.getName());
        UserImage image = UserImage
                .builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(file.getBytes())
                .build();
        userService.updateUserData(userData, principal.getName(), image);
        return ResponseEntity.status(HttpStatus.OK).body("Изменение данных прошло успешно. Требуется повторная аутентификация.");
    }
}
