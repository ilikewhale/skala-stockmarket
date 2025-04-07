package com.example.demo.service.stock;

import com.example.demo.domain.stock.Stock;
import com.example.demo.dto.stock.request.CreateStockRequest;
import com.example.demo.dto.stock.request.UpdatePriceRequest;
import com.example.demo.dto.stock.response.CreateStockResponse;
import com.example.demo.dto.stock.response.StockResponse;
import com.example.demo.repository.stock.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    public CreateStockResponse create(CreateStockRequest request) {
        return new CreateStockResponse(stockRepository.save(request.toEntity()));
    }

    public List<StockResponse> findAll() {
        return stockRepository.findAll()
                .stream().map(StockResponse::new).toList();
    }

    public StockResponse findStockByStockName(String stockName) {
        return new StockResponse(stockRepository.findByStockName(stockName));
    }

    public StockResponse updatePriceByName(String stockName, UpdatePriceRequest request) {
        Stock stock = stockRepository.findByStockName(stockName);
        stock.updatePrice(request.getNewPrice());
        return new StockResponse(stock);
    }

    public void deleteStockByName(String stockName) {
        Stock stock = stockRepository.findByStockName(stockName);
        stockRepository.delete(stock);
    }
}
