package com.example.demo.dto.stock.request;

import com.example.demo.domain.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateStockRequest {
    private String stockName;
    private Double price;

    public Stock toEntity() {
        return Stock.builder()
                .stockName(stockName)
                .price(price)
                .build();
    }
}
