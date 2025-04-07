package com.example.demo.dto.playerStock.response;

import com.example.demo.domain.player.Player;
import com.example.demo.domain.playerStock.PlayerStock;
import com.example.demo.domain.stock.Stock;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlayerStockResponse {

    private final Long playerStockId;
    private final Player player;
    private final Stock stock;
    private final Long quantity;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public PlayerStockResponse(PlayerStock playerStock) {
        this.playerStockId = playerStock.getPlayerStockId();
        this.player = playerStock.getPlayer();
        this.stock = playerStock.getStock();
        this.quantity = playerStock.getQuantity();
        this.createdAt = playerStock.getCreatedAt();
        this.updatedAt = playerStock.getUpdatedAt();
    }
}
