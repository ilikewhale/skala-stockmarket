package com.example.demo.service;

import com.example.demo.domain.Player;
import com.example.demo.domain.PlayerStock;
import com.example.demo.domain.Stock;
import com.example.demo.dto.player.response.PlayerResponse;
import com.example.demo.dto.playerStock.request.CreatePlayerStockRequest;
import com.example.demo.dto.playerStock.request.DeletePlayerStockRequest;
import com.example.demo.dto.playerStock.request.UpdateQuantityRequest;
import com.example.demo.dto.playerStock.response.CreatePlayerStockResponse;
import com.example.demo.dto.playerStock.response.PlayerStockResponse;
import com.example.demo.dto.stock.response.StockResponse;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.PlayerStockRepository;
import com.example.demo.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PlayerStockService {

    private final PlayerRepository playerRepository;
    private final StockRepository stockRepository;
    private final PlayerStockRepository playerStockRepository;

    public CreatePlayerStockResponse create(CreatePlayerStockRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(request.getPlayerId());
        Stock stock = stockRepository.findById(request.getStockId())
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND STOCK " + request.getStockId()));
        if (!player.getPlayerPw().equals(request.getPlayerPw())) {
            throw new IllegalArgumentException("Incorrect pw");
        }

        player.payMoney(request.getQuantity() * stock.getPrice());
        playerRepository.save(player);

        List<PlayerStock> playerStocks = playerStockRepository.findByPlayer(player);
        PlayerStock existingPlayerStock = null;

        if (playerStocks != null) {
            for (PlayerStock ps : playerStocks) {
                if (ps.getStock().getStockId().equals(request.getStockId())) {
                    existingPlayerStock = ps;
                    break;
                }
            }
            if (existingPlayerStock != null) {
                existingPlayerStock.addQuantity(request.getQuantity());
                playerStockRepository.save(existingPlayerStock);
                return new CreatePlayerStockResponse(existingPlayerStock);
            }
        }
        PlayerStock newPlayerStock = PlayerStock.builder()
                .player(player)
                .stock(stock)
                .quantity(request.getQuantity())
                .build();
        return new CreatePlayerStockResponse(playerStockRepository.save(newPlayerStock));
    }

    public void delete(DeletePlayerStockRequest request) {
        PlayerStock playerStock = playerStockRepository.findById(request.getPlayerStockId())
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND player stock " + request.getPlayerStockId()));
        Player player = playerStock.getPlayer();
        Stock stock = playerStock.getStock();
        if (player.getPlayerPw().equals(request.getPlayerPw())) {
            player.addMoney(stock.getPrice() * playerStock.getQuantity());
            playerRepository.save(player);

            playerStockRepository.delete(playerStock);
            return;
        }
        throw new IllegalArgumentException("Incorrect pw");
    }

    public List<PlayerStockResponse> findAll() {
        return playerStockRepository.findAll()
                .stream().map(PlayerStockResponse::new).toList();
    }

    public PlayerStockResponse updateQuantity(UpdateQuantityRequest request) {
        PlayerStock playerStock = playerStockRepository.findById(request.getPlayerStockId())
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND player stock " + request.getPlayerStockId()));
        Player player = playerStock.getPlayer();
        Stock stock = playerStock.getStock();
        if (player.getPlayerPw().equals(request.getPlayerPw())) {
            playerStock.updateQuantity(playerStock.getQuantity() - request.getReduceQuantity());
            playerStockRepository.save(playerStock);

            player.addMoney(stock.getPrice() * playerStock.getQuantity());
            playerRepository.save(player);
            return new PlayerStockResponse(playerStock);
        }
        throw new IllegalArgumentException("Incorrect pw");
    }

    public List<StockResponse> findStockByPlayer(String playerId) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        return playerStockRepository.findByPlayer(player)
                .stream().map(x -> new StockResponse(x.getStock())).toList();
    }

    public List<PlayerResponse> findPlayerByStock(String stockName) {
        Stock stock = stockRepository.findByStockName(stockName);
        return playerStockRepository.findByStock(stock)
                .stream().map(x -> new PlayerResponse(x.getPlayer())).toList();
    }
}
