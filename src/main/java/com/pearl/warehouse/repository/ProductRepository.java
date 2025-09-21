package com.pearl.warehouse.repository;

import com.pearl.warehouse.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findByCode(String code);

    Optional<Product> findByProductName(String productName);

    Optional<Product> findByProductNameContaining(String productName);

    @Query("select p from Product p where p.category.id=?1 and p.productName=?2")
    List<Product> findByProductNameAndCategoryId(Integer categoryName, String productName); // @Param("categoryName")

    @Modifying
    @Query("update Product p set p.code=?1 where p.productId=?2")
    int updateProductCode(String code, Long id);

    @Modifying
    @Query("delete From Product p where p.code=?1")
    void deleteProductByCode(String code);

}
