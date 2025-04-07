package com.example.demo.controller.playerStock;

import com.example.demo.dto.player.response.PlayerResponse;
import com.example.demo.dto.playerStock.request.CreatePlayerStockRequest;
import com.example.demo.dto.playerStock.request.DeletePlayerStockRequest;
import com.example.demo.dto.playerStock.request.UpdateQuantityRequest;
import com.example.demo.dto.playerStock.response.CreatePlayerStockResponse;
import com.example.demo.dto.playerStock.response.PlayerStockResponse;
import com.example.demo.dto.stock.response.StockResponse;
import com.example.demo.service.playerStock.PlayerStockService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Buy stock", description = "Sell some stock")
    @PostMapping("/buy")
    public ResponseEntity<CreatePlayerStockResponse> createPlayerStock(@RequestBody CreatePlayerStockRequest request) {
        CreatePlayerStockResponse createPlayerStockResponse = playerStockService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createPlayerStockResponse);
    }

    @Operation(summary = "Sell all stock", description = "Sell all stock")
    @PostMapping("/sell/all")
    public ResponseEntity<Void> deletePlayerStock(@RequestBody DeletePlayerStockRequest request) {
        playerStockService.delete(request);
        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "Sell some stock", description = "Sell some stock")
    @PatchMapping("/sell")
    public ResponseEntity<PlayerStockResponse> updateQuantityByPw(@RequestBody UpdateQuantityRequest request) {
        PlayerStockResponse playerStockResponse = playerStockService.updateQuantity(request);
        return ResponseEntity.ok()
                .body(playerStockResponse);
    }

    @Operation(summary = "Find all player - stock", description = "Find all player - stock")
    @GetMapping
    public ResponseEntity<List<PlayerStockResponse>> findAll() {
        List<PlayerStockResponse> playerStockResponses = playerStockService.findAll();
        return ResponseEntity.ok()
                .body(playerStockResponses);
    }

    @Operation(summary = "Find player own stock", description = "Find player own stock")
    @GetMapping("/{playerId}/all")
    public ResponseEntity<List<PlayerStockResponse>> findStockByPlayerId(@PathVariable(name = "playerId") String playerId) {
        List<PlayerStockResponse> stockResponses = playerStockService.findStockByPlayer(playerId);
        return ResponseEntity.ok()
                .body(stockResponses);
    }

    @Operation(summary = "Find player by stock name", description = "Find player by stock name")
    @GetMapping("/{stockName}/stockholder")
    public ResponseEntity<List<PlayerStockResponse>> findPlayerByStockName(@PathVariable(name = "stockName") String stockName) {
        List<PlayerStockResponse> playerResponses = playerStockService.findPlayerByStock(stockName);
        return ResponseEntity.ok()
                .body(playerResponses);
    }
}
