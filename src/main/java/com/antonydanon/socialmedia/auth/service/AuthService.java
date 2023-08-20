package com.antonydanon.socialmedia.auth.service;

import com.antonydanon.socialmedia.auth.model.AuthRequestDto;
import com.antonydanon.socialmedia.auth.model.AuthResponseDto;
import com.antonydanon.socialmedia.auth.model.RegisterRequestDto;

public interface AuthService {

    AuthResponseDto register(RegisterRequestDto userRegisterRequestDto);

    AuthResponseDto login(AuthRequestDto authRequestDto);
}
