package com.deeRav.controllers;

import com.deeRav.payloads.ApiResponse;
import com.deeRav.payloads.CategoryDto;
import com.deeRav.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    // create
    @PostMapping("/")
    public ResponseEntity<CategoryDto>createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }


    // update

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id")Integer id){
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<>(updatedCategory,HttpStatus.OK);
    }


    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>deleteCategory(@PathVariable("id")Integer id){
        this.categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category deleted",true),HttpStatus.OK);

    }


    // get
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto>getCategoryById(@PathVariable("id")Integer id){
        CategoryDto categoryById = this.categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryById,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>>getAllCateCory(){
        List<CategoryDto> allCategory = this.categoryService.getAllCategory();
        return new ResponseEntity<>(allCategory,HttpStatus.OK);
    }
}
