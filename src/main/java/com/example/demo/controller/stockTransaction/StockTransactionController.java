package com.example.demo.controller.stockTransaction;

import com.example.demo.dto.stockTransaction.StockTransactionResponse;
import com.example.demo.service.stockTransaction.StockTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transaction")
public class StockTransactionController {

    private final StockTransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<StockTransactionResponse>> findAll() {
        List<StockTransactionResponse> transactionResponses = transactionService.findAll();
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @GetMapping("/player/{playerId}/all")
    public ResponseEntity<List<StockTransactionResponse>> findAllByPlayerId(@PathVariable(name = "playerId") String playerId) {
        List<StockTransactionResponse> transactionResponses = transactionService.findByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @GetMapping("/player/{playerId}/buy")
    public ResponseEntity<List<StockTransactionResponse>> findBuyByPlayerId(@PathVariable(name = "playerId") String playerId) {
        List<StockTransactionResponse> transactionResponses = transactionService.findBuyByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @GetMapping("/player/{playerId}/sell")
    public ResponseEntity<List<StockTransactionResponse>> findSellByPlayerId(@PathVariable(name = "playerId") String playerId) {
        List<StockTransactionResponse> transactionResponses = transactionService.findSellByPlayerId(playerId);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @GetMapping("/stock/{stockName}/all")
    public ResponseEntity<List<StockTransactionResponse>> findAllByStockId(@PathVariable(name = "stockName") String stockName) {
        List<StockTransactionResponse> transactionResponses = transactionService.findByStockName(stockName);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @GetMapping("/stock/{stockName}/buy")
    public ResponseEntity<List<StockTransactionResponse>> findBuyByStockName(@PathVariable(name = "stockName") String stockName) {
        List<StockTransactionResponse> transactionResponses = transactionService.findBuyByStockName(stockName);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }

    @GetMapping("/stock/{stockName}/sell")
    public ResponseEntity<List<StockTransactionResponse>> findSellByStockName(@PathVariable(name = "stockName") String stockName) {
        List<StockTransactionResponse> transactionResponses = transactionService.findSellByStockName(stockName);
        return ResponseEntity.ok()
                .body(transactionResponses);
    }
}
