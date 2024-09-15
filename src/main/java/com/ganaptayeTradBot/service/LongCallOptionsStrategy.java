package com.ganaptayeTradBot.service;

import com.ganaptayeTradBot.KotakNeoService;
import com.ganaptayeTradBot.DataAnalyzer;
import com.ganaptayeTradBot.TradeExecutor;
import com.ganaptayeTradBot.TradeLogger;
import com.ganaptayeTradBot.TradeSignal;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class LongCallOptionsStrategy {

    private final KotakNeoService kotakNeoService;
    private final DataAnalyzer dataAnalyzer;
    private final TradeExecutor tradeExecutor;
    private final TradeLogger tradeLogger;

    public LongCallOptionsStrategy(KotakNeoService kotakNeoService, DataAnalyzer dataAnalyzer, 
                                   TradeExecutor tradeExecutor, TradeLogger tradeLogger) {
        this.kotakNeoService = kotakNeoService;
        this.dataAnalyzer = dataAnalyzer;
        this.tradeExecutor = tradeExecutor;
        this.tradeLogger = tradeLogger;
    }

    public void executeStrategy() {
        try {
            // Fetch market data
            String marketData = kotakNeoService.fetchMarketData();

            // Analyze data
            TradeSignal tradeSignal = dataAnalyzer.analyzeForLongCallOptions(marketData);

            // Place trade based on analysis
            if (tradeSignal.shouldTrade()) {
                tradeExecutor.placeLongCallOrder(tradeSignal.getTradeDetails());
                tradeLogger.logTrade(tradeSignal.getTradeDetails());
            }
        } catch (IOException e) {
            tradeLogger.logError("Error executing Long Call Options strategy: " + e.getMessage());
        }
    }
}
