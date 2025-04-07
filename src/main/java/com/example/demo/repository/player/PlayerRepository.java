package com.example.demo.repository.player;

import com.example.demo.domain.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Player findPlayerByPlayerId(String playerId);

    void deleteByPlayerId(String playerId);
}
