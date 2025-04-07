package com.example.demo.repository.stock;

import com.example.demo.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Stock findByStockName(String stockName);
}
