package com.deeRav.payloads;

import com.deeRav.entity.Post;
import com.deeRav.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Integer id;
    private String content;

//    private User user;
//
//    private Post post;



}
