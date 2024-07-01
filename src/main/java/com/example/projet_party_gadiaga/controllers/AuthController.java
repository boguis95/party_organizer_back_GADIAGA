package com.example.projet_party_gadiaga.controllers;

import com.example.projet_party_gadiaga.dtos.UserDTO;
import com.example.projet_party_gadiaga.entities.User;
import com.example.projet_party_gadiaga.services.AuthService;
import com.example.projet_party_gadiaga.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody UserDTO userToLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userToLogin.getEmail(),
                        userToLogin.getPassword()
                )
        );
        UserDTO userDTO = userService.findByEmail(userToLogin.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@RequestBody  UserDTO userToRegistre) {
        try {
            UserDTO registeredUser = authService.registerUser(userToRegistre);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            throw e;
        }
    }
}