package com.example.demo.dto.playerStock.response;

import com.example.demo.domain.Player;
import com.example.demo.domain.PlayerStock;
import com.example.demo.domain.Stock;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePlayerStockResponse {
    private final Player player;
    private final Stock stock;
    private final Long quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;


    public CreatePlayerStockResponse(PlayerStock playerStock) {
        this.player = playerStock.getPlayer();
        this.stock = playerStock.getStock();
        this.quantity = playerStock.getQuantity();
        this.createdAt = playerStock.getCreatedAt();
        this.updatedAt = playerStock.getUpdatedAt();
    }
}
