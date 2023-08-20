package com.antonydanon.socialmedia.auth.service;

import com.antonydanon.socialmedia.auth.exception.UserAlreadyExistsException;
import com.antonydanon.socialmedia.auth.model.AuthRequestDto;
import com.antonydanon.socialmedia.auth.model.AuthResponseDto;
import com.antonydanon.socialmedia.auth.model.RegisterRequestDto;
import com.antonydanon.socialmedia.security.jwt.JwtService;
import com.antonydanon.socialmedia.user.model.User;
import com.antonydanon.socialmedia.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DefaultAuthService implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto dto) {
        checkUserExists(dto);
        User newUser = userService.save(toUser(dto));
        String token = jwtService.generateToken(newUser);
        log.info("User with id: {} registered!", newUser.getId());
        return new AuthResponseDto(token);
    }

    @Override
    public AuthResponseDto login(AuthRequestDto authRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword())
        );
        User user = (User) userService.loadUserByUsername(authRequestDto.getEmail());
        return new AuthResponseDto(jwtService.generateToken(user));
    }

    private void checkUserExists(RegisterRequestDto dto) {
        if (userService.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("User with such an email already exists.");
        }
    }

    private User toUser(RegisterRequestDto registerRequestDto) {
        return new User()
                .setName(registerRequestDto.getName())
                .setEmail(registerRequestDto.getEmail())
                .setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
    }
}

