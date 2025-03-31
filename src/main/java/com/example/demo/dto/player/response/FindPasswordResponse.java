package com.example.demo.dto.player.response;

import com.example.demo.domain.player.Player;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FindPasswordResponse {

    private final String playerPw;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime updatedAt;

    public FindPasswordResponse(Player player) {
        this.playerPw = player.getPlayerPw();
        this.updatedAt = player.getUpdatedAt();
    }
}
