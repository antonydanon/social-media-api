package com.antonydanon.socialmedia.post.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostCreateDto {
    private String title;

    private String content;
}
