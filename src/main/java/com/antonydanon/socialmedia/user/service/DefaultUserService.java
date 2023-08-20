package com.antonydanon.socialmedia.user.service;

import com.antonydanon.socialmedia.user.exception.UserNotFoundException;
import com.antonydanon.socialmedia.user.model.User;
import com.antonydanon.socialmedia.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) {
        return userRepository
                .findByEmail(username)
                .orElseThrow(() -> new UserNotFoundException("User hasn't been found."));
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
