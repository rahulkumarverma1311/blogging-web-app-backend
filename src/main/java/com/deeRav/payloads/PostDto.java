package com.deeRav.payloads;

import com.deeRav.entity.Category;
import com.deeRav.entity.Comment;
import com.deeRav.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private Integer postId;
    private String title;
    private String content;

    private  String imageUrl;
    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

    private Set<CommentDto> comments= new HashSet<>();
}
