package com.pearl.warehouse.dto.input;

import java.util.List;

public record CategoryWithProducts(Integer id, String name, List<ProductInput> products) {
}
