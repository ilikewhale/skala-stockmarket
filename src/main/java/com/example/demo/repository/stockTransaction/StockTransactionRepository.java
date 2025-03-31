package com.example.demo.repository.stockTransaction;

import com.example.demo.domain.stockTransaction.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long> {
    List<StockTransaction> findByPlayerId(String playerId);

    List<StockTransaction> findByStockName(String stockName);
}
