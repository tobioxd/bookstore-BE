package com.project.bookstore.services;

import java.util.List;

import com.project.bookstore.dtos.CategoryDTO;
import com.project.bookstore.models.Category;

public interface ICategoryService {

    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryById(long id);

    List<Category> getAllCategories();

    Category updateCategory(long categoryId, CategoryDTO categoryDTO);

    void deleteCategory(long id);

}
