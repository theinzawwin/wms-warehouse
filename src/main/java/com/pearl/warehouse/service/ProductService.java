package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.ProductInput;
import com.pearl.warehouse.exceptions.NameDuplicateException;
import com.pearl.warehouse.model.Category;
import com.pearl.warehouse.model.Product;
import com.pearl.warehouse.repository.CategoryRepository;
import com.pearl.warehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Optional<Product> findById(Integer id){
        return productRepository.findById(id);
    }

    public Product saveProduct(ProductInput productInput){
        Optional<Product> optionalProduct = productRepository.findByCode(productInput.code());
        if(optionalProduct.isPresent()){
            throw new NameDuplicateException("Product Code is already exist");
        }
        Product product = new Product();
        Optional<Category> category = categoryRepository.findById(productInput.categoryId());
        product.setProductName(productInput.productName());
        product.setCode(productInput.code());
        product.setCategory(category.get());
        Product createdProduct=productRepository.save(product);
        return createdProduct;
    }
    public Product update(Integer id,ProductInput newProd){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            Optional<Category> category = categoryRepository.findById(newProd.categoryId());
            Product existProduct = product.get();
            existProduct.setProductName(newProd.productName());
            existProduct.setCode(newProd.code());
            existProduct.setCategory(category.get());
            return productRepository.save(existProduct);
        }else {
            throw new RuntimeException("Item Not Found");
        }
    }
    public Boolean deleteById(Integer id){
        productRepository.deleteById(id);
        return true;
    }
}
