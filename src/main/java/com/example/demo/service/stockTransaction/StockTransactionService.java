package com.example.demo.service.stockTransaction;

import com.example.demo.domain.stockTransaction.StockTransaction;
import com.example.demo.dto.stockTransaction.StockTransactionResponse;
import com.example.demo.repository.stockTransaction.StockTransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockTransactionService {
    private final StockTransactionRepository transactionRepository;

    public List<StockTransactionResponse> findAll() {
        return transactionRepository.findAll()
                .stream().map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findByPlayerId(String playerId) {
        return transactionRepository.findByPlayerIdOrderByGeneratedAtDesc(playerId)
                .stream().map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findBuyByPlayerId(String playerId) {
        return transactionRepository.findByPlayerIdOrderByGeneratedAtDesc(playerId)
                .stream().filter(this::isBuy).map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findSellByPlayerId(String playerId) {
        return transactionRepository.findByPlayerIdOrderByGeneratedAtDesc(playerId)
                .stream().filter(this::isSell).map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findByStockName(String stockName) {
        return transactionRepository.findByStockNameOrderByGeneratedAtDesc(stockName)
                .stream().map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findBuyByStockName(String stockName) {
        return transactionRepository.findByStockNameOrderByGeneratedAtDesc(stockName)
                .stream().filter(this::isBuy).map(StockTransactionResponse::new).toList();
    }

    public List<StockTransactionResponse> findSellByStockName(String stockName) {
        return transactionRepository.findByStockNameOrderByGeneratedAtDesc(stockName)
                .stream().filter(this::isSell).map(StockTransactionResponse::new).toList();
    }

    private boolean isBuy(StockTransaction transaction) {
        return transaction.getTransactionType().equals("매입");
    }

    private boolean isSell(StockTransaction transaction) {
        return transaction.getTransactionType().equals("매도");
    }
}
