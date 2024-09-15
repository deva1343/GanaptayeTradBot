package com.ganaptayeTradBot;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DataAnalyzer {

    // Method to analyze data for Long Call Options
    public TradeSignal analyzeForLongCallOptions(String marketData) {
        JSONObject jsonData = new JSONObject(marketData);
        double price = jsonData.optDouble("price");
        String symbol = jsonData.optString("symbol");

        // Basic example logic for Long Call Options trade signal
        if (price > 100) {
            return new TradeSignal(true, "{\"symbol\":\"" + symbol + "\",\"action\":\"BUY_CALL\",\"price\":" + price + "}");
        } else {
            return new TradeSignal(false, null);
        }
    }

    // Method to analyze data for Long Put Options
    public TradeSignal analyzeForLongPutOptions(String marketData) {
        JSONObject jsonData = new JSONObject(marketData);
        double price = jsonData.optDouble("price");
        String symbol = jsonData.optString("symbol");

        // Basic example logic for Long Put Options trade signal
        if (price < 100) {
            return new TradeSignal(true, "{\"symbol\":\"" + symbol + "\",\"action\":\"BUY_PUT\",\"price\":" + price + "}");
        } else {
            return new TradeSignal(false, null);
        }
    }
}
