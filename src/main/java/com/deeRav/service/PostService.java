package com.deeRav.service;

import com.deeRav.entity.Post;
import com.deeRav.payloads.PostDto;
import com.deeRav.payloads.PostResponse;

import javax.xml.transform.sax.SAXResult;
import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto , Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostDto getPostById(Integer postId);

    PostResponse getPostsByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);

    PostResponse getPostsByUser(Integer userId,Integer pageNumber,Integer pazeSize);

    List<PostDto>searchPost(String keyWord);




}
