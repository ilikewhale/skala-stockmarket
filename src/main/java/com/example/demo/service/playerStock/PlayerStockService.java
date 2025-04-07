package com.example.demo.service.playerStock;

import com.example.demo.domain.player.Player;
import com.example.demo.domain.playerStock.PlayerStock;
import com.example.demo.domain.stock.Stock;
import com.example.demo.domain.stockTransaction.StockTransaction;
import com.example.demo.dto.playerStock.request.CreatePlayerStockRequest;
import com.example.demo.dto.playerStock.request.DeletePlayerStockRequest;
import com.example.demo.dto.playerStock.request.UpdateQuantityRequest;
import com.example.demo.dto.playerStock.response.CreatePlayerStockResponse;
import com.example.demo.dto.playerStock.response.PlayerStockResponse;
import com.example.demo.repository.player.PlayerRepository;
import com.example.demo.repository.playerStock.PlayerStockRepository;
import com.example.demo.repository.stock.StockRepository;
import com.example.demo.repository.stockTransaction.StockTransactionRepository;
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
    private final StockTransactionRepository stockTransactionRepository;

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

        stockTransactionRepository.save(
                StockTransaction.builder()
                        .playerId(player.getPlayerId())
                        .stockName(stock.getStockName())
                        .transactionType("매입")
                        .transactionQuantity(request.getQuantity())
                        .buyPrice(stock.getPrice())
                        .totalPrice(stock.getPrice() * request.getQuantity())
                        .build()
        );

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

        if (!player.getPlayerPw().equals(request.getPlayerPw())) {
            throw new IllegalArgumentException("Incorrect pw");
        }

        player.addMoney(stock.getPrice() * playerStock.getQuantity());
        playerRepository.save(player);

        stockTransactionRepository.save(
                StockTransaction.builder()
                        .playerId(player.getPlayerId())
                        .stockName(stock.getStockName())
                        .transactionType("매도")
                        .transactionQuantity(playerStock.getQuantity())
                        .buyPrice(stock.getPrice())
                        .totalPrice(stock.getPrice() * playerStock.getQuantity())
                        .build()
        );

        playerStockRepository.delete(playerStock);
    }

    public PlayerStockResponse updateQuantity(UpdateQuantityRequest request) {
        PlayerStock playerStock = playerStockRepository.findById(request.getPlayerStockId())
                .orElseThrow(() -> new IllegalArgumentException("NOT FOUND player stock " + request.getPlayerStockId()));
        Player player = playerStock.getPlayer();
        Stock stock = playerStock.getStock();

        if (!player.getPlayerPw().equals(request.getPlayerPw())) {
            throw new IllegalArgumentException("Incorrect pw");
        }

        Long reduceQuantity = request.getReduceQuantity();

        playerStock.reduceQuantity(reduceQuantity);
        playerStockRepository.save(playerStock);

        player.addMoney(stock.getPrice() * reduceQuantity);
        playerRepository.save(player);

        stockTransactionRepository.save(
                StockTransaction.builder()
                        .playerId(player.getPlayerId())
                        .stockName(stock.getStockName())
                        .transactionType("매도")
                        .transactionQuantity(reduceQuantity)
                        .buyPrice(stock.getPrice())
                        .totalPrice(stock.getPrice() * reduceQuantity)
                        .build()
        );

        return new PlayerStockResponse(playerStock);
    }


    public List<PlayerStockResponse> findAll() {
        return playerStockRepository.findAll()
                .stream().map(PlayerStockResponse::new).toList();
    }

    public List<PlayerStockResponse> findStockByPlayer(String playerId) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        return playerStockRepository.findByPlayer(player)
                .stream().map(PlayerStockResponse::new).toList();
    }

    public List<PlayerStockResponse> findPlayerByStock(String stockName) {
        Stock stock = stockRepository.findByStockName(stockName);
        return playerStockRepository.findByStock(stock)
                .stream().map(PlayerStockResponse::new).toList();
    }
}
