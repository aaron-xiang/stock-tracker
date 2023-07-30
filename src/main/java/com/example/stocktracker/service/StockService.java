package com.example.stocktracker.service;

import com.example.stocktracker.model.Stock;
import com.example.stocktracker.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockService {
    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Optional<Stock> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public Stock getStockByName(String name) {
        return stockRepository.findByName(name);
    }

    public Stock getStockBySymbol(String symbol) {
        return stockRepository.findBySymbol(symbol);
    }

    public Stock createNewStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock updateStock(Long id, Stock replacementStock) {
        Optional<Stock> existingStock = stockRepository.findById(id);
        if (existingStock.isPresent()) {
            Stock stock = existingStock.get();
            stock.setName(replacementStock.getName());
            stock.setSymbol(replacementStock.getSymbol());
            stock.setPrice(replacementStock.getPrice());
            stock.setUser(replacementStock.getUser());
            return stockRepository.save(stock);
        }
        else return null;
    }

    public boolean deleteStock(Long id) {
        if (stockRepository.existsById(id)) {
            stockRepository.deleteById(id);
            return true;
        }
        else return false;
    }
}
