package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.ProductDto;
import com.pearl.warehouse.dto.api.ApiResponse;
import com.pearl.warehouse.dto.api.Pagination;
import com.pearl.warehouse.dto.input.ProductInput;
import com.pearl.warehouse.dto.response.ProductResponse;
import com.pearl.warehouse.model.Product;
import com.pearl.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("{id}/product-detail")
    public Optional<Product> getProductById(@PathVariable(value="id") Integer id){
        return productService.findById(id);
    }

    @GetMapping("/find-by-name")
    public Optional<Product> getProductByName(@RequestParam(value="productName") String productName){
        return productService.findByProductName(productName);
    }
    @GetMapping("{productId}/updateCode")
    public ResponseEntity<Boolean> updateProductCode(@PathVariable("productId")Long productId,@RequestParam("code") String productCode){
        return ResponseEntity.ok(productService.updateProductCode(productId,productCode));
    }
    @GetMapping("/get-products-by-categoryName")
    public List<Product> getProductsByCategoryName(@RequestParam(value="categoryId") Integer categoryId, @RequestParam(value="productName")String productName){
        return productService.findProductsByCategoryName(categoryId, productName);
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
    @DeleteMapping("/deleteByCode/{code}")
    public Boolean deleteProductByCode(@PathVariable String code){
        return productService.deleteProductCode(code);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "productId") String sortBy,
            @RequestParam(defaultValue = "DESC") String direction) {

        Page<ProductResponse> productPage =
                productService.getProducts(page, size, search, sortBy, direction);
        Pagination pagination = new Pagination(
                productPage.getNumber(),
                productPage.getSize(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.isLast()
        );

        ApiResponse<List<ProductResponse>> response =
                ApiResponse.success(
                        productPage.getContent(),
                        "Products fetched successfully"
                );
        response.setPagination(pagination);

        return ResponseEntity.ok(response);
    }
}
