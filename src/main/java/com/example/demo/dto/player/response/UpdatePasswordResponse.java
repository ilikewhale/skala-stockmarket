package com.example.demo.dto.player.response;

import com.example.demo.domain.player.Player;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePasswordResponse {
    private final String playerId;
    private final String playerPassword;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public UpdatePasswordResponse(Player player) {
        this.playerId = player.getPlayerId();
        this.playerPassword = player.getPlayerPw();
        this.updatedAt = player.getUpdatedAt();
    }
}
