package com.antonydanon.socialmedia.post.repository;

import com.antonydanon.socialmedia.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);

    @Query(value = "SELECT p FROM Post p WHERE p.user.id != ?1")
    List<Post> findAllFromOtherUsers(Long userId);
}
