package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.ProductDto;
import com.pearl.warehouse.dto.input.ProductInput;
import com.pearl.warehouse.model.Product;
import com.pearl.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/greeting")
    public ResponseEntity<ProductDto> greeting(){
        ProductDto item1 =new ProductDto();
        item1.setItemCode("001");
        item1.setItemName("Item 1");
        return ResponseEntity.ok(item1);
    }

    @GetMapping("/list")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Optional<Product> getProductById(@PathVariable(value="id") Integer id){
        return productService.findById(id);
    }


    @PostMapping("/save")
    public Product saveProduct(@RequestBody ProductInput productInput){
        return productService.saveProduct(productInput);
    }
    @PutMapping("/update/{id}")
    public Product updateProduct(@PathVariable(value="id") Integer productId,@RequestBody ProductInput productInput){
        return productService.update(productId,productInput);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteProduct(@PathVariable Integer id){
        return productService.deleteById(id);
    }
}
