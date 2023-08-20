package com.antonydanon.socialmedia.auth.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RegisterRequestDto {

    private String name;

    private String email;

    private String password;
}
