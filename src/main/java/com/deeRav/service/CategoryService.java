package com.deeRav.service;

import com.deeRav.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    CategoryDto updateCategory(CategoryDto categoryDto,Integer catagoryId);

    void deleteCategory(Integer catagoryId);

    CategoryDto getCategoryById(Integer catagoryId);


    List<CategoryDto>getAllCategory();

}
