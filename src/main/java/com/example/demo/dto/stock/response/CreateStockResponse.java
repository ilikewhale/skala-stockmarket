package com.example.demo.dto.stock.response;

import com.example.demo.domain.stock.Stock;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateStockResponse {

    private final Long stockId;
    private final String stockName;
    private final Long price;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public CreateStockResponse(Stock stock) {
        this.stockId = stock.getStockId();
        this.stockName = stock.getStockName();
        this.price = stock.getPrice();
        this.createdAt = stock.getCreatedAt();
        this.updatedAt = stock.getUpdatedAt();
    }
}
