package com.ganaptayeTradBot;

import java.io.IOException;

public class TradeExecutor {
    private final KotakNeoService kotakNeoService;

    public TradeExecutor(KotakNeoService kotakNeoService) {
        this.kotakNeoService = kotakNeoService;
    }

    public void placeLongPutOrder(String tradeDetails) throws IOException {
        // Example implementation for placing a long put order
        kotakNeoService.placeTradeOrder(tradeDetails, "PUT");
    }

    public void placeLongCallOrder(String tradeDetails) throws IOException {
        // Example implementation for placing a long call order
        kotakNeoService.placeTradeOrder(tradeDetails, "CALL");
    }
}
