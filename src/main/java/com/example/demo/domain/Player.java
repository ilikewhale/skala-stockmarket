package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "player_id", nullable = false, unique = true)
    private String playerId;

    @Column(name = "player_pw", nullable = false)
    private String playerPw;

    @Column(name = "confirmation", nullable = false)
    private String confirmation;

    @Column(name = "player_money")
    private long playerMoney;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Player(String playerId, String playerPw, String confirmation, long playerMoney) {
        this.playerId = playerId;
        this.playerPw = playerPw;
        this.confirmation = confirmation;
        this.playerMoney = playerMoney;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String playerPw, String confirmation, long playerMoney) {
        this.playerPw = playerPw;
        this.confirmation = confirmation;
        this.playerMoney = playerMoney;
    }

    public void updatePw(String newPassword) {
        this.playerPw = newPassword;
    }

    public void updateConfirmation(String newConfirmation) {
        this.confirmation = newConfirmation;
    }

    public void addMoney(Long extraPlayerMoney) {
        this.playerMoney += extraPlayerMoney;
    }

    public void withdrawMoney(Long withdrawMoney) {
        if (playerMoney - withdrawMoney < 0) {
            throw new IllegalArgumentException("More money entered than you have");
        }
        this.playerMoney -= withdrawMoney;
    }
}
