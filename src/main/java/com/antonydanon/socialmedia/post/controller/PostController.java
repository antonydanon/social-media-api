package com.antonydanon.socialmedia.post.controller;

import com.antonydanon.socialmedia.post.model.Post;
import com.antonydanon.socialmedia.post.model.PostCreateDto;
import com.antonydanon.socialmedia.post.model.PostUpdateDto;
import com.antonydanon.socialmedia.post.service.PostService;
import com.antonydanon.socialmedia.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> findAll(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(postService.findAllFromOtherUsers(user.getId()));
    }

    @GetMapping("/my")
    public ResponseEntity<List<Post>> getUserPost(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(postService.findByUserId(user.getId()));
    }
    @PostMapping
    public ResponseEntity<String> createPost(
            @AuthenticationPrincipal User user,
            @RequestBody PostCreateDto dto) {
        postService.create(dto, user.getEmail());
        return ResponseEntity.ok("Post has been created.");
    }

    @PatchMapping
    public ResponseEntity<Post> updatePost(@RequestBody PostUpdateDto dto) {
        Post post = postService.update(dto);
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.ok("Post has been deleted.");
    }
}
