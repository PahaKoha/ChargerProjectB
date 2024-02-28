package com.backend.PowerUp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainPageController {
    @GetMapping("/lock")
    public ResponseEntity<?> lock(Principal principal) {
        Map<String, Object> body = new HashMap<>();
        body.put("username", principal.getName());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
