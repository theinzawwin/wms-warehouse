package com.pearl.warehouse.repository;

import com.pearl.warehouse.dto.response.StockResponse;
import com.pearl.warehouse.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {

    @Query("select new com.pearl.warehouse.dto.response.StockResponse(s.id,s.price,s.quantity,s.uom, p.productId, p.productName) FROM Stock s JOIN s.product p")
    List<StockResponse> findAllStocksWithProductDetails();
}
