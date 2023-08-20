package com.antonydanon.socialmedia.auth.controller;

import com.antonydanon.socialmedia.auth.model.AuthRequestDto;
import com.antonydanon.socialmedia.auth.model.AuthResponseDto;
import com.antonydanon.socialmedia.auth.model.RegisterRequestDto;
import com.antonydanon.socialmedia.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerUser(@RequestBody RegisterRequestDto registerRequestDto) {
        return ResponseEntity.ok(authService.register(registerRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        return ResponseEntity.ok(authService.login(authRequestDto));
    }
}
