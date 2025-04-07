package com.example.demo.repository.playerStock;

import com.example.demo.domain.player.Player;
import com.example.demo.domain.playerStock.PlayerStock;
import com.example.demo.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerStockRepository extends JpaRepository<PlayerStock, Long> {
    List<PlayerStock> findByPlayer(Player player);

    List<PlayerStock> findByStock(Stock stock);
}
