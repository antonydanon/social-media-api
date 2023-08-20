package com.antonydanon.socialmedia.post.service;

import com.antonydanon.socialmedia.post.exception.PostNotFoundException;
import com.antonydanon.socialmedia.post.model.Post;
import com.antonydanon.socialmedia.post.model.PostCreateDto;
import com.antonydanon.socialmedia.post.model.PostUpdateDto;
import com.antonydanon.socialmedia.post.repository.PostRepository;
import com.antonydanon.socialmedia.user.model.User;
import com.antonydanon.socialmedia.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultPostService implements PostService {

    private final UserService userService;
    private final PostRepository postRepository;

    @Override
    public List<Post> findAllFromOtherUsers(Long userId) {
        return postRepository.findAllFromOtherUsers(userId);
    }

    @Override
    public List<Post> findByUserId(Long userId) {
        return postRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Post create(PostCreateDto dto, String userEmail) {
        User user = (User) userService.loadUserByUsername(userEmail);
        Post post = toPost(dto, user);
        Post createdPost = postRepository.save(post);
        log.info("New post with id: {} has been created by user with id: {}", createdPost.getId(), user.getId());
        return createdPost;
    }

    @Override
    public Post update(PostUpdateDto dto) {
        Post post = postRepository.findById(dto.getId())
                .orElseThrow(() -> new PostNotFoundException("Post hasn't been found."));
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());
        return postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    private Post toPost(PostCreateDto dto, User user) {
            return new Post()
                    .setTitle(dto.getTitle())
                    .setContent(dto.getContent())
                    .setUser(user);
    }
}
