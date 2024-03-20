package com.backend.PowerUp.controllers;

import com.backend.PowerUp.dto.MainUserData;
import com.backend.PowerUp.entities.User;
import com.backend.PowerUp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MainPageController {

    private final UserService userService;
    @GetMapping("/lock")
    public ResponseEntity<?> lock(Principal principal) {
        MainUserData mainUserData = userService.getMainUserData(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(mainUserData);
    }
}
