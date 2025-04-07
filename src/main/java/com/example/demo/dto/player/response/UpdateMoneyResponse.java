package com.example.demo.dto.player.response;

import com.example.demo.domain.player.Player;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateMoneyResponse {
    private final String playerId;
    private final Long playerMoney;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public UpdateMoneyResponse(Player player) {
        this.playerId = player.getPlayerId();
        this.playerMoney = player.getPlayerMoney();
        this.updatedAt = player.getUpdatedAt();
    }
}
