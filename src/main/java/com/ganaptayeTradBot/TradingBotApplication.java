package com.ganaptayeTradBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import com.ganaptayeTradBot.service.LongCallOptionsStrategy;
import com.ganaptayeTradBot.service.LongPutOptionsStrategy;
import com.ganaptayeTradBot.service.TradingBotService;
import com.ganaptayeTradBot.KotakNeoService;
import com.ganaptayeTradBot.DataAnalyzer;
import com.ganaptayeTradBot.TradeExecutor;
import com.ganaptayeTradBot.TradeLogger;
import com.ganaptayeTradBot.auth.KotakNeoAuthService;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ganaptayeTradBot", "com.ganaptayeTradBot.auth", "com.ganaptayeTradBot.service"})
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

    @Bean
    public KotakNeoAuthService kotakNeoAuthService() {
        return new KotakNeoAuthService();
    }

    @Bean
    public KotakNeoService kotakNeoService() {
        return new KotakNeoService();
    }

    @Bean
    public TradeExecutor tradeExecutor(KotakNeoService kotakNeoService) {
        return new TradeExecutor(kotakNeoService);
    }

    @Bean
    public TradeLogger tradeLogger() {
        return new TradeLogger();
    }

    @Bean
    public LongCallOptionsStrategy longCallOptionsStrategy(KotakNeoService kotakNeoService,
                                                           DataAnalyzer dataAnalyzer,
                                                           TradeExecutor tradeExecutor,
                                                           TradeLogger tradeLogger) {
        return new LongCallOptionsStrategy(kotakNeoService, dataAnalyzer, tradeExecutor, tradeLogger);
    }

    @Bean
    public LongPutOptionsStrategy longPutOptionsStrategy(KotakNeoService kotakNeoService,
                                                         DataAnalyzer dataAnalyzer,
                                                         TradeExecutor tradeExecutor,
                                                         TradeLogger tradeLogger) {
        return new LongPutOptionsStrategy(kotakNeoService, dataAnalyzer, tradeExecutor, tradeLogger);
    }
}
