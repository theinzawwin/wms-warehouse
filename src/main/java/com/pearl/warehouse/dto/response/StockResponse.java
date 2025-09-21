package com.pearl.warehouse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StockResponse {

    private Long id;
    private Integer  quantity;
    private String uom;
    private Long productId;
    private String productName;
    private Double price;
    public StockResponse(Long id,Double price, Integer  quantity,  String uom, Long productId, String productName){
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.uom = uom;
        this.productId = productId;
        this.productName = productName;
    }
}
