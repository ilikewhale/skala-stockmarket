package com.example.demo.service.player;

import com.example.demo.domain.player.Player;
import com.example.demo.dto.player.request.*;
import com.example.demo.dto.player.response.*;
import com.example.demo.repository.player.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public CreatePlayerResponse create(CreatePlayerRequest request) {
        return new CreatePlayerResponse(playerRepository.save(request.toEntity()));
    }

    public PlayerResponse findByPlayerId(String playerId) {
        return new PlayerResponse(playerRepository.findPlayerByPlayerId(playerId));
    }

    public List<PlayerResponse> findAll() {
        List<Player> players = playerRepository.findAll();
        return players.stream().map(PlayerResponse::new).toList();
    }

    public void deleteByPlayerId(String playerId) {
        playerRepository.deleteByPlayerId(playerId);
    }

    public UpdatePasswordResponse updatePwByConfirmation(String playerId, UpdatePasswordRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        if (player.getPlayerPw().equals(request.getPlayerPassword()) && player.getConfirmation().equals(request.getConfirmation())) {
            player.updatePw(request.getNewPassword());
            return new UpdatePasswordResponse(player);
        }
        throw new IllegalArgumentException("Incorrect pw or confirmation");
    }

    public UpdateConfirmationResponse updateConfirmationByPw(String playerId, UpdateConfirmationRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        if (player.getPlayerPw().equals(request.getPlayerPassword())) {
            player.updateConfirmation(request.getNewConfirmation());
            return new UpdateConfirmationResponse(player);
        }
        throw new IllegalArgumentException("Incorrect pw");
    }

    public UpdateMoneyResponse addMoneyByPw(String playerId, AddMoneyRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        if (player.getPlayerPw().equals(request.getPlayerPassword())) {
            player.addMoney(request.getExtraPlayerMoney());
            return new UpdateMoneyResponse(player);
        }
        throw new IllegalArgumentException("Incorrect pw");
    }

    public UpdateMoneyResponse withdrawMoneyByPw(String playerId, WithdrawMoneyRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        if (player.getPlayerPw().equals(request.getPlayerPassword())) {
            player.withdrawMoney(request.getWithdrawMoney());
            return new UpdateMoneyResponse(player);
        }
        throw new IllegalArgumentException("Incorrect pw");
    }

    public UpdatePlayerResponse update(String playerId, UpdatePlayerRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        player.update(
                request.getPlayerPw(),
                request.getConfirmation(),
                request.getPlayerMoney()
        );
        return new UpdatePlayerResponse(player);
    }

    public FindPasswordResponse findPwByConfirmation(String playerId, FindPasswordRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        if (player.getConfirmation().equals(request.getConfirmation())) {
            return new FindPasswordResponse(player);
        }
        throw new IllegalArgumentException("Incorrect confirmation");
    }

    public Boolean loginByPw(String playerId, LoginRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        return player.getPlayerPw().equals(request.getPlayerPassword());
    }

    public FindConfirmationResponse findConfirmationByPw(String playerId, FindConfirmationRequest request) {
        Player player = playerRepository.findPlayerByPlayerId(playerId);
        if (player.getPlayerPw().equals(request.getPlayerPw())) {
            return new FindConfirmationResponse(player);
        }
        throw new IllegalArgumentException("Incorrect pw");
    }
}
