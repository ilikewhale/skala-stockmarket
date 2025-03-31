package com.example.demo.repository;

import com.example.demo.domain.Player;
import com.example.demo.domain.PlayerStock;
import com.example.demo.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface PlayerStockRepository extends JpaRepository<PlayerStock, Long> {
    List<PlayerStock> findByPlayer(Player player);

    List<PlayerStock> findByStock(Stock stock);
}
