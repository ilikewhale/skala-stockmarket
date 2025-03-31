package com.example.demo.controller;

import com.example.demo.dto.player.response.PlayerResponse;
import com.example.demo.dto.playerStock.request.CreatePlayerStockRequest;
import com.example.demo.dto.playerStock.request.DeletePlayerStockRequest;
import com.example.demo.dto.playerStock.request.UpdateQuantityRequest;
import com.example.demo.dto.playerStock.response.CreatePlayerStockResponse;
import com.example.demo.dto.playerStock.response.PlayerStockResponse;
import com.example.demo.dto.stock.response.StockResponse;
import com.example.demo.service.PlayerStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/player/stock")
public class PlayerStockController {

    private final PlayerStockService playerStockService;

    @PostMapping("/buy")
    public ResponseEntity<CreatePlayerStockResponse> createPlayerStock(@RequestBody CreatePlayerStockRequest request) {
        CreatePlayerStockResponse createPlayerStockResponse = playerStockService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createPlayerStockResponse);
    }

    @PostMapping("/sell/all")
    public ResponseEntity<Void> deletePlayerStock(@RequestBody DeletePlayerStockRequest request) {
        playerStockService.delete(request);
        return ResponseEntity.ok()
                .build();
    }

    @PatchMapping("/sell")
    public ResponseEntity<PlayerStockResponse> updateQuantityByPw(@RequestBody UpdateQuantityRequest request) {
        PlayerStockResponse playerStockResponse = playerStockService.updateQuantity(request);
        return ResponseEntity.ok()
                .body(playerStockResponse);
    }

    @GetMapping
    public ResponseEntity<List<PlayerStockResponse>> findAll() {
        List<PlayerStockResponse> playerStockResponses = playerStockService.findAll();
        return ResponseEntity.ok()
                .body(playerStockResponses);
    }

    @GetMapping("/{playerId}/all")
    public ResponseEntity<List<StockResponse>> findStockByPlayerId(@PathVariable(name = "playerId") String playerId) {
        List<StockResponse> stockResponses = playerStockService.findStockByPlayer(playerId);
        return ResponseEntity.ok()
                .body(stockResponses);
    }

    @GetMapping("/{stockName}/stockholder")
    public ResponseEntity<List<PlayerResponse>> findPlayerByStockName(@PathVariable(name = "stockName") String stockName) {
        List<PlayerResponse> playerResponses = playerStockService.findPlayerByStock(stockName);
        return ResponseEntity.ok()
                .body(playerResponses);
    }
}
