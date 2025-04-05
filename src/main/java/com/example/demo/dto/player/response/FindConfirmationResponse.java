package com.example.demo.dto.player.response;

import com.example.demo.domain.player.Player;
import lombok.Getter;

@Getter
public class FindConfirmationResponse {

    private final String confirmation;

    public FindConfirmationResponse(Player player) {
        this.confirmation = player.getConfirmation();
    }
}
