package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.input.StockInput;
import com.pearl.warehouse.model.Stock;
import com.pearl.warehouse.service.StockService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/list")
    public String list(){
        return "stock list";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/save")
    public ResponseEntity<Stock> saveStock(@Valid @RequestBody StockInput stockInput){
        return ResponseEntity.ok(stockService.saveStock(stockInput));
    }
}
