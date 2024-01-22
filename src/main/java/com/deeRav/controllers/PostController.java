package com.deeRav.controllers;

import com.deeRav.config.WebAppConstant;
import com.deeRav.entity.Post;
import com.deeRav.payloads.ApiResponse;
import com.deeRav.payloads.PostDto;
import com.deeRav.payloads.PostResponse;
import com.deeRav.service.PostService;
import com.deeRav.serviceImpl.FileServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {


    @Autowired
    private PostService postService;

    @Autowired
    private FileServiceImpl fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable("userId") Integer userId, @PathVariable("categoryId")Integer categoryId){
        PostDto post = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    // get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse>getPostByUser(@PathVariable("userId")Integer userId, @RequestParam(value = WebAppConstant.PAGE_NUMBER,defaultValue = "0",required = false)Integer pageNumber,@RequestParam(value = WebAppConstant.PAGE_SIZE,defaultValue = "3",required = false)Integer pageSize){
        PostResponse postsByUser = this.postService.getPostsByUser(userId,pageNumber,pageSize);
        return new ResponseEntity<>(postsByUser,HttpStatus.OK);
    }

    // get by category

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse>getPostByCategory(@PathVariable("categoryId") Integer categoryId, @RequestParam(value = WebAppConstant.PAGE_NUMBER,defaultValue = "0",required = false)Integer pageNumber,
                                                         @RequestParam(value = WebAppConstant.PAGE_SIZE,defaultValue = "3",required = false)Integer pageSize){

        PostResponse postsByCategory = this.postService.getPostsByCategory(categoryId, pageNumber, pageSize);
        return new ResponseEntity<>(postsByCategory,HttpStatus.OK);
    }


    // get All Post
    @GetMapping("/posts")
    public ResponseEntity<PostResponse>getAllPosts(
            @RequestParam(value = "pageNumber",defaultValue = WebAppConstant.PAGE_NUMBER,required = false)Integer pageNumber, @RequestParam(value = "pageSize",defaultValue = WebAppConstant.PAGE_SIZE,required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = WebAppConstant.SORT_BY,required = false)String sortBy, @RequestParam(value = "sortDir",defaultValue = WebAppConstant.SORT_DIR,required = false)String sortDir
    ){
        PostResponse allPost = this.postService.getAllPost(pageNumber, pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allPost,HttpStatus.OK);
    }

    // get single post by id
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto>getPostById(@PathVariable("id")Integer id){
        PostDto postById = this.postService.getPostById(id);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }


    // delete post by id
    @DeleteMapping("posts/{id}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable("id")Integer id){
        this.postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Post Deleted",true),HttpStatus.OK);
    }

    @PutMapping("posts/{id}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto , @PathVariable("id")Integer id){
        PostDto post = this.postService.updatePost(postDto, id);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    // search
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable("keyword")String keyword){
        List<PostDto> postDtos = this.postService.searchPost(keyword);
        return new ResponseEntity<>(postDtos,HttpStatus.OK);

    }

    // serve file

    @GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());

    }
}
