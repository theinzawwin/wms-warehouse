package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.ProductInput;
import com.pearl.warehouse.dto.response.ProductResponse;
import com.pearl.warehouse.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductInput dto);

    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "category.name", target = "categoryName")
    ProductResponse toResponse(Product product);
}
