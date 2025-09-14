package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.StockInput;
import com.pearl.warehouse.model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ProductMapper.class})
public interface StockMapper {
    @Mapping(source = "productId",target = "product.productId")
    Stock toEntity(StockInput dto);

}
