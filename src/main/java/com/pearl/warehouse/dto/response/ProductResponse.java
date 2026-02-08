package com.pearl.warehouse.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProductResponse {

    private Long productId;
    private String productName;
    private String code;
    private Boolean status;
    private Long categoryId;
    private String categoryName;

    public ProductResponse(Long productId, String productName, String code,
                           Boolean status, Long categoryId, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.code = code;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // getters & setters
}
