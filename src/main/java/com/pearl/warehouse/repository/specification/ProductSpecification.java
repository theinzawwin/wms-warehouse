package com.pearl.warehouse.repository.specification;

import com.pearl.warehouse.model.Category;
import com.pearl.warehouse.model.Product;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {
    public static Specification<Product> search(String keyword) {
        return (root, query, cb) -> {

            if (keyword == null || keyword.isBlank()) {
                return cb.conjunction();
            }

            String like = "%" + keyword.toLowerCase() + "%";

            Join<Product, Category> categoryJoin =
                    root.join("category", JoinType.LEFT);

            return cb.or(
                    cb.like(cb.lower(root.get("productName")), like),
                    cb.like(cb.lower(root.get("code")), like),
                    cb.like(cb.lower(categoryJoin.get("name")), like)
            );
        };
    }
}
