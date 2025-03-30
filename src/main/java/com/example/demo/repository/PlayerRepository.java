package com.example.demo.repository;

import com.example.demo.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findPlayerByPlayerId(String playerId);

    void deleteByPlayerId(String playerId);
}
