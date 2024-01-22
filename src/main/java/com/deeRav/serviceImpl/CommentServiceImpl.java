package com.deeRav.serviceImpl;

import com.deeRav.entity.Comment;
import com.deeRav.entity.Post;
import com.deeRav.entity.User;
import com.deeRav.exception.ResourceNotFound;
import com.deeRav.payloads.CommentDto;
import com.deeRav.repository.CommentRepo;
import com.deeRav.repository.PostRepo;
import com.deeRav.repository.UserRepo;
import com.deeRav.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(Integer postId,Integer userId, CommentDto commentDto) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", postId));
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "userId", userId));
        Comment comment = this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment saved = this.commentRepo.save(comment);

        return this.modelMapper.map(saved, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFound("comment", "commentId", commentId));
        this.commentRepo.delete(comment);

    }
}
