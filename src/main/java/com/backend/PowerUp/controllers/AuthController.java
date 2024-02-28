package com.backend.PowerUp.controllers;

import com.backend.PowerUp.dto.JWTRequest;
import com.backend.PowerUp.dto.JWTResponse;
import com.backend.PowerUp.dto.RegistrationUserDTO;
import com.backend.PowerUp.entities.User;
import com.backend.PowerUp.exceptions.UserAlreadyExistException;
import com.backend.PowerUp.services.UserService;
import com.backend.PowerUp.utils.JWTTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JWTTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@RequestBody RegistrationUserDTO registrationUserDTO) {
        User user = User.builder()
                .username(registrationUserDTO.getUsername())
                .email(registrationUserDTO.getEmail())
                .password(passwordEncoder.encode(registrationUserDTO.getPassword()))
                .build();
        try {
            userService.createNewUser(user);
        } catch (UserAlreadyExistException exception) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JWTRequest userAuthData) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAuthData.getUsername(), userAuthData.getPassword()));
        } catch (BadCredentialsException exception) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(userAuthData.getUsername());
        String token = jwtTokenUtils.generateJWT(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    @GetMapping("/home")
    public ResponseEntity<?> test() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verifyToken")
    public Map<String, Boolean> verifyToken() {
        return new HashMap<>(Map.of("status", true));
    }
}
