package com.pearl.warehouse.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CategoryWithProducts(Integer id, @NotBlank(message = "Name must not be empty") String name, List<ProductInput> products) {
}
