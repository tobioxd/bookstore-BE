package com.project.bookstore.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.bookstore.dtos.CategoryDTO;
import com.project.bookstore.models.Category;
import com.project.bookstore.services.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/categories")
// @Validated
@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }


    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<?> createCategory(
            @Valid @RequestBody CategoryDTO categoryDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage) 
                .toList();

            return ResponseEntity.badRequest().body(errorMessages);
        } 
        categoryService.createCategory(categoryDTO); 
        return ResponseEntity.ok("Insert category successfully  !");
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<String> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDTO categoryDTO) {
        categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok("update Category " + id + " successfully!");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("delete Category " + id + " successfully!");
    }

}
