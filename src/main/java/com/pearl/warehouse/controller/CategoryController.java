package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.CategoryInput;
import com.pearl.warehouse.dto.input.CategoryWithProducts;
import com.pearl.warehouse.model.Category;
import com.pearl.warehouse.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public Category saveCategory(@RequestBody CategoryInput categoryInput){
        return categoryService.saveCategory(categoryInput);
    }

    @PostMapping("/saveWithProducts")
    public Category saveCategoryWithProducts(@RequestBody CategoryWithProducts categoryInput){
        return categoryService.saveCategoryWithProducts(categoryInput);
    }

    @GetMapping("/list")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategory();
    }
    @GetMapping("{id}")
    public Optional<Category> findById(@PathVariable(value = "id") Integer id){
        return categoryService.findById(id);
    }

    @PutMapping("/update/{id}")
    public Category updateCategory(@PathVariable Integer id,@RequestBody CategoryInput categoryInput){
        return categoryService.updateCategory(id,categoryInput);
    }
    @DeleteMapping("/delete/{id}")
    public Boolean deleteCategory(@PathVariable Integer id){
        return categoryService.deleteById(id);
    }
}
