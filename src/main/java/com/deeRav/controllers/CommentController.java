package com.deeRav.controllers;

import com.deeRav.payloads.ApiResponse;
import com.deeRav.payloads.CommentDto;
import com.deeRav.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("comments/post/{postId}/user/{userId}")
    public ResponseEntity<CommentDto>createComment(@PathVariable("postId")Integer postId,@PathVariable("userId")Integer userId, @RequestBody CommentDto commentDto){
        CommentDto comment = this.commentService.createComment(postId, userId,commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
    @DeleteMapping("comment/{commentId}")
    public ResponseEntity<ApiResponse>deleteComment(@PathVariable("commentId")Integer commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("comment deleted...",true),HttpStatus.OK);

    }
}
