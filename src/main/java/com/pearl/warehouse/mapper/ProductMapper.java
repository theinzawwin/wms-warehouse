package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.ProductInput;
import com.pearl.warehouse.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductInput dto);
}
