package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.StockInput;
import com.pearl.warehouse.mapper.StockMapper;
import com.pearl.warehouse.model.Stock;
import com.pearl.warehouse.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    private final StockMapper stockMapper;

    public StockService(StockMapper stockMapper){
        this.stockMapper = stockMapper;
    }
    public Stock saveStock(StockInput stockInput){
        Stock stock = stockMapper.toEntity(stockInput);
        Stock stockCreated=stockRepository.save(stock);
        return stockCreated;
    }
}
