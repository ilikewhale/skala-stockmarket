package com.example.demo.controller.player;

import com.example.demo.dto.player.request.*;
import com.example.demo.dto.player.response.*;
import com.example.demo.service.player.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    @Operation(summary = "Create player", description = "Create player")
    @PostMapping
    public ResponseEntity<CreatePlayerResponse> createPlayer(@RequestBody CreatePlayerRequest request) {
        CreatePlayerResponse playerResponse = playerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playerResponse);
    }

    @Operation(summary = "Find player by playerId", description = "Find player by playerId")
    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponse> findPlayerByPlayerId(@PathVariable(name = "playerId") String playerId) {
        PlayerResponse playerResponse = playerService.findByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(playerResponse);
    }

    @Operation(summary = "Find password by confirmation", description = "Find password by confirmation")
    @PostMapping("/{playerId}/pw")
    public ResponseEntity<FindPasswordResponse> findPasswordByConfirmation(@PathVariable(name = "playerId") String playerId, @RequestBody FindPasswordRequest request) {
        FindPasswordResponse findPasswordResponse = playerService.findPwByConfirmation(playerId, request);
        return ResponseEntity.ok()
                .body(findPasswordResponse);
    }

    @Operation(summary = "Find confirmation by password", description = "Find confirmation by password")
    @PostMapping("/{playerId}/confirmation")
    public ResponseEntity<FindConfirmationResponse> findConfirmationByPw(@PathVariable(name = "playerId") String playerId, @RequestBody FindConfirmationRequest request) {
        FindConfirmationResponse findConfirmationResponse = playerService.findConfirmationByPw(playerId, request);
        return ResponseEntity.ok()
                .body(findConfirmationResponse);
    }

    @Operation(summary = "Find all players", description = "Find all players")
    @GetMapping
    public ResponseEntity<List<PlayerResponse>> findAllPlayer() {
        List<PlayerResponse> playerResponses = playerService.findAll();
        return ResponseEntity.ok()
                .body(playerResponses);
    }

    @Operation(summary = "Update player by playerId", description = "Update player by playerId")
    @PatchMapping("/{playerId}")
    public ResponseEntity<UpdatePlayerResponse> updatePlayer(@PathVariable(name = "playerId") String playerId, @RequestBody UpdatePlayerRequest request) {
        UpdatePlayerResponse updatePlayerResponse = playerService.update(playerId, request);
        return ResponseEntity.ok()
                .body(updatePlayerResponse);
    }

    @Operation(summary = "Delete player by playerId", description = "Delete player by playerId")
    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayerByPlayerId(@PathVariable(name = "playerId") String playerId) {
        playerService.deleteByPlayerId(playerId);
        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "Update password by confirmation", description = "Update password by confirmation")
    @PatchMapping("/{playerId}/pw")
    public ResponseEntity<UpdatePasswordResponse> updatePasswordByConfirmation(@PathVariable(name = "playerId") String playerId, @RequestBody UpdatePasswordRequest request) {
        UpdatePasswordResponse playerPasswordResponse = playerService.updatePwByConfirmation(playerId, request);
        return ResponseEntity.ok()
                .body(playerPasswordResponse);
    }

    @Operation(summary = "Update confirmation by password", description = "Update confirmation by password")
    @PatchMapping("/{playerId}/confirmation")
    public ResponseEntity<UpdateConfirmationResponse> updateConfirmationByPassword(@PathVariable(name = "playerId") String playerId, @RequestBody UpdateConfirmationRequest request) {
        UpdateConfirmationResponse playerConfirmationResponse = playerService.updateConfirmationByPw(playerId, request);
        return ResponseEntity.ok()
                .body(playerConfirmationResponse);
    }

    @Operation(summary = "Add Money by password", description = "Add Money by password")
    @PatchMapping("/{playerId}/money/add")
    public ResponseEntity<UpdateMoneyResponse> addMoneyByPassword(@PathVariable(name = "playerId") String playerId, @RequestBody AddMoneyRequest request) {
        UpdateMoneyResponse updateMoneyResponse = playerService.addMoneyByPw(playerId, request);
        return ResponseEntity.ok()
                .body(updateMoneyResponse);
    }

    @Operation(summary = "Withdraw money by password", description = "Withdraw money by password")
    @PatchMapping("/{playerId}/money/withdraw")
    public ResponseEntity<UpdateMoneyResponse> withdrawMoneyByPassword(@PathVariable(name = "playerId") String playerId, @RequestBody WithdrawMoneyRequest request) {
        UpdateMoneyResponse updateMoneyResponse = playerService.withdrawMoneyByPw(playerId, request);
        return ResponseEntity.ok()
                .body(updateMoneyResponse);
    }

    @Operation(summary = "Login", description = "Login")
    @PostMapping("/{playerId}/login")
    public ResponseEntity<Boolean> login(@PathVariable(name = "playerId") String playerId, @RequestBody LoginRequest request) {
        Boolean login = playerService.loginByPw(playerId, request);
        return ResponseEntity.ok()
                .body(login);
    }
}
