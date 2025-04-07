package com.example.demo.config;

import com.example.demo.domain.stock.Stock;
import com.example.demo.repository.stock.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initStocks(StockRepository stockRepository) {
        return args -> {
            if (stockRepository.count() == 0) {
                stockRepository.save(Stock.builder().stockName("Skala1").price(70000L).build());
                stockRepository.save(Stock.builder().stockName("Skala2").price(55000L).build());
                stockRepository.save(Stock.builder().stockName("Skala3").price(80000L).build());
                stockRepository.save(Stock.builder().stockName("Skala4").price(13131L).build());
                stockRepository.save(Stock.builder().stockName("Skala5").price(29172L).build());
                stockRepository.save(Stock.builder().stockName("Skala6").price(920372L).build());
                stockRepository.save(Stock.builder().stockName("Skala7").price(8283L).build());
                stockRepository.save(Stock.builder().stockName("Skala8").price(9418L).build());
                stockRepository.save(Stock.builder().stockName("Skala9").price(11238L).build());
                stockRepository.save(Stock.builder().stockName("Skala10").price(70234L).build());
                System.out.println("Init data save succeeded");
            }
        };
    }
}
