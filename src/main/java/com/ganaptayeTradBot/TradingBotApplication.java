package com.ganaptayeTradBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.ganaptayeTradBot.service.LongCallOptionsStrategy;
import com.ganaptayeTradBot.service.LongPutOptionsStrategy;
import com.ganaptayeTradBot.service.TradingBotService;
import com.ganaptayeTradBot.auth.KotakNeoAuthService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ganaptayeTradBot"})
public class TradingBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingBotApplication.class, args);
    }

    @Bean
    public TradingBotService tradingBotService(KotakNeoService kotakNeoService, 
                                               DataAnalyzer dataAnalyzer, 
                                               TradeExecutor tradeExecutor, 
                                               TradeLogger tradeLogger, 
                                               LongCallOptionsStrategy longCallOptionsStrategy, 
                                               LongPutOptionsStrategy longPutOptionsStrategy,
                                               KotakNeoAuthService kotakNeoAuthService) {
        return new TradingBotService(kotakNeoService, dataAnalyzer, tradeExecutor, tradeLogger, 
                                     longCallOptionsStrategy, longPutOptionsStrategy, kotakNeoAuthService);
    }

    @Bean
    public DataAnalyzer dataAnalyzer() {
        return new DataAnalyzer();
    }
}
