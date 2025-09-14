package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.CategoryInput;
import com.pearl.warehouse.dto.input.CategoryWithProducts;
import com.pearl.warehouse.model.Category;
import com.pearl.warehouse.model.Product;
import com.pearl.warehouse.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory(){
        return categoryRepository.findAll();
    }
    public Category saveCategory(CategoryInput categoryInput){
        Category category=new Category();
        category.setName(categoryInput.name());
        return categoryRepository.save(category);
    }

    public Category saveCategoryWithProducts(CategoryWithProducts categoryInput){
        Category category=new Category();
        category.setName(categoryInput.name());
        List<Product> productList;
        if(!categoryInput.products().isEmpty()){
            productList = new ArrayList<>();
            categoryInput.products().forEach(p->{
                productList.add(new Product(p.id(),p.productName(),p.code(),category));
            });
            category.setProductList(productList);
        }
        return categoryRepository.save(category);
    }

    public Category updateCategory(Integer id, CategoryInput categoryInput){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setName(categoryInput.name());
            return categoryRepository.save(existingCategory);
        }else{
            throw new RuntimeException("Category Not found");
        }
    }
    public Optional<Category> findById(Integer id){
        return categoryRepository.findById(id);
    }
    public Boolean deleteById(Integer id){
        categoryRepository.deleteById(id);
        return true;
    }
}
