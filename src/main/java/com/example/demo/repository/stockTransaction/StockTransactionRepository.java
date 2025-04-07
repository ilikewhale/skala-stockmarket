package com.example.demo.repository.stockTransaction;

import com.example.demo.domain.stockTransaction.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {
    List<StockTransaction> findByPlayerIdOrderByGeneratedAtDesc(String playerId);

    List<StockTransaction> findByStockNameOrderByGeneratedAtDesc(String stockName);
}
