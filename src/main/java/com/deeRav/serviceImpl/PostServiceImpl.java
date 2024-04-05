package com.deeRav.serviceImpl;

import com.deeRav.entity.Category;
import com.deeRav.entity.Post;
import com.deeRav.entity.User;
import com.deeRav.exception.ResourceNotFound;

import com.deeRav.payloads.PostDto;
import com.deeRav.payloads.PostResponse;
import com.deeRav.repository.CategoryRepo;
import com.deeRav.repository.PostRepo;
import com.deeRav.repository.UserRepo;
import com.deeRav.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user","userId",String.valueOf(userId)));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category","categoryId",String.valueOf(categoryId)));
        Post post = this.modelMapper.map(postDto,Post.class);
        post.setTitle(postDto.getTitle());
        post.setImageUrl("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post save = this.postRepo.save(post);
        return this.modelMapper.map(save,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", String.valueOf(postId)));
        post.setTitle(postDto.getTitle());
        post.setImageUrl(postDto.getImageUrl());
        post.setContent(postDto.getContent());
        Post save = this.postRepo.save(post);
        return this.modelMapper.map(save,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", String.valueOf(postId)));
        this.postRepo.delete(post);

    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort = null;
        if(sortDir.equalsIgnoreCase("ase")){
            sort = Sort.by(sortBy).ascending();
        }else{
            sort = Sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> postList = pagePost.getContent();
        List<PostDto> postDtos = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalePages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFound("post", "postId", String.valueOf(postId)));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber,Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber,pageSize);
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFound("category","categoryId",String.valueOf(categoryId)));

        Page<Post> byCategory = this.postRepo.findByCategory(category,p);

        List<PostDto> postDtos = byCategory.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setTotalElements(byCategory.getTotalElements());
        postResponse.setTotalePages(byCategory.getTotalPages());
        postResponse.setPageNumber(byCategory.getNumber());
        postResponse.setPageSize(byCategory.getSize());
        postResponse.setLastPage(byCategory.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getPostsByUser(Integer userId,Integer pageNumber,Integer pageSize) {

        Pageable p = PageRequest.of(pageNumber,pageSize);
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "userId", String.valueOf(userId)));
        Page<Post> posts = this.postRepo.findByUser(user,p);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalePages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> searchPost(String keyWord) {
        List<Post> byTitleContaining = this.postRepo.findByTitleContaining(keyWord);
        List<PostDto> postDtos = byTitleContaining.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }


}
