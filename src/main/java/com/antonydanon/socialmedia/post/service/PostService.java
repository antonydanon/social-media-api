package com.antonydanon.socialmedia.post.service;

import com.antonydanon.socialmedia.post.model.Post;
import com.antonydanon.socialmedia.post.model.PostCreateDto;
import com.antonydanon.socialmedia.post.model.PostUpdateDto;

import java.util.List;

public interface PostService {

    public List<Post> findAllFromOtherUsers(Long userId);

    public List<Post> findByUserId(Long userId);

    Post create(PostCreateDto dto, String userEmail);

    Post update(PostUpdateDto dto);

    void delete(Long id);
}
