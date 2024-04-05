package com.deeRav.serviceImpl;

import com.deeRav.entity.Category;
import com.deeRav.exception.ResourceNotFound;
import com.deeRav.payloads.CategoryDto;
import com.deeRav.repository.CategoryRepo;
import com.deeRav.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = this.modelMapper.map(categoryDto,Category.class);
        Category saveCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(saveCategory,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer catagoryId) {
        Category cat = this.categoryRepo.findById(catagoryId).orElseThrow(() -> new ResourceNotFound("Category","categoryId",String.valueOf(catagoryId)));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category savedCategory = this.categoryRepo.save(cat);
        return this.modelMapper.map(savedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer catagoryId) {
        Category cat = this.categoryRepo.findById(catagoryId).orElseThrow(() -> new ResourceNotFound("Category","categoryId",String.valueOf(catagoryId)));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategoryById(Integer catagoryId) {
        Category cat = this.categoryRepo.findById(catagoryId).orElseThrow(() -> new ResourceNotFound("Category","categoryId",String.valueOf(catagoryId)));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory(){
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;

    }
}
