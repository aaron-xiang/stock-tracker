package com.example.stocktracker.controller;

import com.example.stocktracker.model.Stock;
import com.example.stocktracker.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StockController {
    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/stocks/{id}")
    public ResponseEntity<Stock> getStockById(@PathVariable Long id) {
        Optional<Stock> stock = stockService.getStockById(id);
        if (stock.isPresent()) {
            return ResponseEntity.ok(stock.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/stocks")
    public ResponseEntity<Stock> createNewStock(Stock stock) {
        Stock createdStock = stockService.createNewStock(stock);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStock);
    }

    @PostMapping("/stocks/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody Stock stock) {
        Stock updatedStock = stockService.updateStock(id, stock);
        if (updatedStock != null) {
            return ResponseEntity.ok(updatedStock);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/stocks/{id}")
    public ResponseEntity<Stock> deleteStock(@PathVariable Long id) {
        boolean deletedStock = stockService.deleteStock(id);
        if (deletedStock) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
