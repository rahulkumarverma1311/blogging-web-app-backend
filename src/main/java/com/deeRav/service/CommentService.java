package com.deeRav.service;

import com.deeRav.payloads.CommentDto;

public interface CommentService {


    CommentDto createComment(Integer postId,Integer userId,CommentDto commentDto);
    void deleteComment(Integer commentId);
}
