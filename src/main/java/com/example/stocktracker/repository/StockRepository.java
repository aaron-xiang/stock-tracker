package com.example.stocktracker.repository;

import com.example.stocktracker.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findById(Long id);

    Stock findByName(String name);

    Stock findBySymbol(String symbol);
}
