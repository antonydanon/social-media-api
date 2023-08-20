package com.antonydanon.socialmedia.user.service;

import com.antonydanon.socialmedia.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Boolean existsByEmail(String email);

    User save(User user);
}
