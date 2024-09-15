package com.ganaptayeTradBot.service;

import com.ganaptayeTradBot.KotakNeoService;
import com.ganaptayeTradBot.DataAnalyzer;
import com.ganaptayeTradBot.TradeExecutor;
import com.ganaptayeTradBot.TradeLogger;
import com.ganaptayeTradBot.TradeSignal;

import java.io.IOException;

import org.springframework.stereotype.Service;

@Service
public class LongPutOptionsStrategy {

    private final KotakNeoService kotakNeoService;
    private final DataAnalyzer dataAnalyzer;
    private final TradeExecutor tradeExecutor;
    private final TradeLogger tradeLogger;

    public LongPutOptionsStrategy(KotakNeoService kotakNeoService, DataAnalyzer dataAnalyzer, 
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
            TradeSignal tradeSignal = dataAnalyzer.analyzeForLongPutOptions(marketData);

            // Place trade based on analysis
            if (tradeSignal.shouldTrade()) {
                tradeExecutor.placeLongPutOrder(tradeSignal.getTradeDetails());
                tradeLogger.logTrade(tradeSignal.getTradeDetails());
            }
        } catch (IOException e) {
            tradeLogger.logError("Error executing Long Put Options strategy: " + e.getMessage());
        }
    }
}
