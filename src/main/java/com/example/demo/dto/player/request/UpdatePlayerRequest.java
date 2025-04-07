package com.example.demo.dto.player.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UpdatePlayerRequest {
    private String playerPw;
    private Long playerMoney;
    private String confirmation;
}
