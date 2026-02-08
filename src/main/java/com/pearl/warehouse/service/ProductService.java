package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.ProductInput;
import com.pearl.warehouse.dto.response.ProductResponse;
import com.pearl.warehouse.exceptions.NameDuplicateException;
import com.pearl.warehouse.mapper.ProductMapper;
import com.pearl.warehouse.model.Category;
import com.pearl.warehouse.model.Product;
import com.pearl.warehouse.repository.CategoryRepository;
import com.pearl.warehouse.repository.ProductRepository;
import com.pearl.warehouse.repository.specification.ProductSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.rmi.NotBoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProductService {

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "productId", "productName", "code", "status"
    );
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

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

    public Optional<Product> findByProductName(String productName){
        return productRepository.findByProductNameContaining(productName);
    }

    public List<Product> findProductsByCategoryName(Integer categoryId,String productName){
        return productRepository.findByProductNameAndCategoryId(categoryId,productName);
    }

    @Transactional
    public boolean updateProductCode(Long productId,String code){
        int resultCount =productRepository.updateProductCode(code,productId);
        return resultCount > 0;
    }

    @Transactional
    public boolean deleteProductCode(String code){
       productRepository.deleteProductByCode(code);
        return true;
    }

    public Page<ProductResponse> getProducts(
            int page,
            int size,
            String search,
            String sortBy,
            String direction) {

        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            sortBy = "productId";
        }

        Sort sort = "DESC".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Product> spec = ProductSpecification.search(search);

        Page<Product> productPage =
                productRepository.findAll(spec, pageable);

        return productPage.map(productMapper::toResponse);
    }

}
