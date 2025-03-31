package com.example.demo.controller.player;

import com.example.demo.dto.player.request.*;
import com.example.demo.dto.player.response.*;
import com.example.demo.service.player.PlayerService;
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

    @PostMapping
    public ResponseEntity<CreatePlayerResponse> createPlayer(@RequestBody CreatePlayerRequest request) {
        CreatePlayerResponse playerResponse = playerService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(playerResponse);
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerResponse> findPlayerByPlayerId(@PathVariable(name = "playerId") String playerId) {
        PlayerResponse playerResponse = playerService.findByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(playerResponse);
    }

    @PostMapping("/{playerId}/pw")
    public ResponseEntity<FindPasswordResponse> findPasswordByConfirmation(@PathVariable(name = "playerId") String playerId, @RequestBody FindPasswordRequest request) {
        FindPasswordResponse findPasswordResponse = playerService.findPwByConfirmation(playerId, request);
        return ResponseEntity.ok()
                .body(findPasswordResponse);
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponse>> findAllPlayer() {
        List<PlayerResponse> playerResponses = playerService.findAll();
        return ResponseEntity.ok()
                .body(playerResponses);
    }

    @PatchMapping("/{playerId}")
    public ResponseEntity<UpdatePlayerResponse> updatePlayer(@PathVariable(name = "playerId") String playerId, @RequestBody UpdatePlayerRequest request) {
        UpdatePlayerResponse updatePlayerResponse = playerService.update(playerId, request);
        return ResponseEntity.ok()
                .body(updatePlayerResponse);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<Void> deletePlayerByPlayerId(@PathVariable(name = "playerId") String playerId) {
        playerService.deleteByPlayerId(playerId);
        return ResponseEntity.ok()
                .build();
    }

    @PatchMapping("/{playerId}/pw")
    public ResponseEntity<UpdatePasswordResponse> updatePasswordByConfirmation(@PathVariable(name = "playerId") String playerId, @RequestBody UpdatePasswordRequest request) {
        UpdatePasswordResponse playerPasswordResponse = playerService.updatePwByConfirmation(playerId, request);
        return ResponseEntity.ok()
                .body(playerPasswordResponse);
    }

    @PatchMapping("/{playerId}/confirmation")
    public ResponseEntity<UpdateConfirmationResponse> updateConfirmationByPassword(@PathVariable(name = "playerId") String playerId, @RequestBody UpdateConfirmationRequest request) {
        UpdateConfirmationResponse playerConfirmationResponse = playerService.updateConfirmationByPw(playerId, request);
        return ResponseEntity.ok()
                .body(playerConfirmationResponse);
    }

    @PatchMapping("/{playerId}/money/add")
    public ResponseEntity<UpdateMoneyResponse> addMoneyByPassword(@PathVariable(name = "playerId") String playerId, @RequestBody AddMoneyRequest request) {
        UpdateMoneyResponse updateMoneyResponse = playerService.addMoneyByPw(playerId, request);
        return ResponseEntity.ok()
                .body(updateMoneyResponse);
    }

    @PatchMapping("/{playerId}/money/withdraw")
    public ResponseEntity<UpdateMoneyResponse> withdrawMoneyByPassword(@PathVariable(name = "playerId") String playerId, @RequestBody WithdrawMoneyRequest request) {
        UpdateMoneyResponse updateMoneyResponse = playerService.withdrawMoneyByPw(playerId, request);
        return ResponseEntity.ok()
                .body(updateMoneyResponse);
    }

    @PostMapping("/{playerId}/login")
    public ResponseEntity<Boolean> login(@PathVariable(name = "playerId") String playerId, @RequestBody LoginRequest request) {
        Boolean login = playerService.loginByPw(playerId, request);
        return ResponseEntity.ok()
                .body(login);
    }
}
