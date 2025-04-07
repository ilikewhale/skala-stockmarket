package com.example.demo.dto.stockTransaction;

import com.example.demo.domain.stockTransaction.StockTransaction;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class StockTransactionResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime generatedAt;

    private final String playerId;
    private final String stockName;
    private final String transactionType;
    private final Long transactionQuantity;
    private final Long buyPrice;
    private final Long totalPrice;

    public StockTransactionResponse(StockTransaction transaction) {
        this.generatedAt = transaction.getGeneratedAt();
        this.playerId = transaction.getPlayerId();
        this.stockName = transaction.getStockName();
        this.transactionType = transaction.getTransactionType();
        this.transactionQuantity = transaction.getTransactionQuantity();
        this.buyPrice = transaction.getBuyPrice();
        this.totalPrice = transaction.getTotalPrice();
    }
}
