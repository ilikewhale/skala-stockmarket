package com.example.demo.scheduler;

import com.example.demo.domain.stock.Stock;
import com.example.demo.repository.stock.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class StockPriceScheduler {

    private final StockRepository stockRepository;
    private final Random random = new Random();

    // 10초마다 실행 (10000ms)
    @Scheduled(fixedRate = 10000)
    public void updateStockPrices() {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks) {
            double changeRate = (random.nextDouble() * 4 - 2); // -2% ~ +2% 변동
            double newPrice = stock.getPrice() * (1 + changeRate / 100);

            stock.updatePrice((long) (Math.round(newPrice * 100.0) / 100.0)); // 소수점 2자리까지 반올림
            stock.updateChangeRate(changeRate);
            System.out.println("Generated changeRate: " + changeRate);

            stockRepository.save(stock);
        }

        System.out.println("✅ Update stock price");
    }
}
