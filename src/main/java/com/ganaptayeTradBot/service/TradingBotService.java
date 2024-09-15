package com.ganaptayeTradBot.service;

import com.ganaptayeTradBot.KotakNeoService;
import com.ganaptayeTradBot.DataAnalyzer;
import com.ganaptayeTradBot.TradeExecutor;
import com.ganaptayeTradBot.TradeLogger;
import com.ganaptayeTradBot.TradeSignal;
import com.ganaptayeTradBot.auth.KotakNeoAuthService;
import org.springframework.stereotype.Service;

@Service
public class TradingBotService {

    private final KotakNeoService kotakNeoService;
    private final DataAnalyzer dataAnalyzer;
    private final TradeExecutor tradeExecutor;
    private final TradeLogger tradeLogger;
    private final LongCallOptionsStrategy longCallOptionsStrategy;
    private final LongPutOptionsStrategy longPutOptionsStrategy;
    private final KotakNeoAuthService kotakNeoAuthService;

    public TradingBotService(KotakNeoService kotakNeoService, DataAnalyzer dataAnalyzer,
                             TradeExecutor tradeExecutor, TradeLogger tradeLogger,
                             LongCallOptionsStrategy longCallOptionsStrategy,
                             LongPutOptionsStrategy longPutOptionsStrategy,
                             KotakNeoAuthService kotakNeoAuthService) {
        this.kotakNeoService = kotakNeoService;
        this.dataAnalyzer = dataAnalyzer;
        this.tradeExecutor = tradeExecutor;
        this.tradeLogger = tradeLogger;
        this.longCallOptionsStrategy = longCallOptionsStrategy;
        this.longPutOptionsStrategy = longPutOptionsStrategy;
        this.kotakNeoAuthService = kotakNeoAuthService;
    }

    public void run() {
        try {
            // Fetch real-time market data
            String marketData = kotakNeoService.fetchMarketData();

            // Execute strategies
            longCallOptionsStrategy.executeStrategy();
            longPutOptionsStrategy.executeStrategy();

            // Handle authentication if necessary
            // Example: kotakNeoAuthService.getSessionToken("receivedOtp");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
