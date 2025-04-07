package com.example.demo.controller.stock;

import com.example.demo.dto.stock.request.CreateStockRequest;
import com.example.demo.dto.stock.request.UpdatePriceRequest;
import com.example.demo.dto.stock.response.CreateStockResponse;
import com.example.demo.dto.stock.response.StockResponse;
import com.example.demo.service.stock.StockService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService;

    @Operation(summary = "Create stock", description = "Create stock")
    @PostMapping
    public ResponseEntity<CreateStockResponse> createStock(@RequestBody CreateStockRequest request) {
        CreateStockResponse stockResponse = stockService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(stockResponse);
    }

    @Operation(summary = "Find all stocks", description = "Find all stocks")
    @GetMapping
    public ResponseEntity<List<StockResponse>> findAllStocks() {
        List<StockResponse> stockResponses = stockService.findAll();
        return ResponseEntity.ok()
                .body(stockResponses);
    }

    @Operation(summary = "Find stock by stockName", description = "Find stock by stockName")
    @GetMapping("/{stockName}")
    public ResponseEntity<StockResponse> findStockByStockName(@PathVariable(name = "stockName") String stockName) {
        StockResponse stockResponse = stockService.findStockByStockName(stockName);
        return ResponseEntity.ok()
                .body(stockResponse);
    }

    @Operation(summary = "Update price", description = "Update price")
    @PatchMapping("/{stockName}")
    public ResponseEntity<StockResponse> updatePriceByStockName(@PathVariable(name = "stockName") String stockName, @RequestBody UpdatePriceRequest request) {
        StockResponse stockResponse = stockService.updatePriceByName(stockName, request);
        return ResponseEntity.ok()
                .body(stockResponse);
    }

    @Operation(summary = "Delete stock", description = "Delete stock")
    @DeleteMapping("/{stockName}")
    public ResponseEntity<Void> deleteStockByStockName(@PathVariable(name = "stockName") String stockName) {
        stockService.deleteStockByName(stockName);
        return ResponseEntity.ok()
                .build();
    }
}
