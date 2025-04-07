package com.example.demo.dto.player.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WithdrawMoneyRequest {
    private String playerPassword;
    private Long withdrawMoney;
}
