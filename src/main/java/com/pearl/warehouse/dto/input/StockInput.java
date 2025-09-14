package com.pearl.warehouse.dto.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StockInput(@NotNull(message = "Product is required") Long productId, @Min(value = 1,message = "Quantity is must be at least 1") int quantity,@NotNull Double price,@NotBlank(message = "UOM is required") String uom) {
}
