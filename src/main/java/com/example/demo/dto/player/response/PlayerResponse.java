package com.example.demo.dto.player.response;

import com.example.demo.domain.player.Player;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PlayerResponse {
    private final String playerId;
    private final Long playerMoney;
    private final String confirmation;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public PlayerResponse(Player player) {
        this.playerId = player.getPlayerId();
        this.playerMoney = player.getPlayerMoney();
        this.confirmation = player.getConfirmation();
        this.createdAt = player.getCreatedAt();
        this.updatedAt = player.getUpdatedAt();
    }
}
