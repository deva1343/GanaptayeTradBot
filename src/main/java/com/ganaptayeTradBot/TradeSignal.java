package com.ganaptayeTradBot;

public class TradeSignal {
    private boolean shouldTrade;
    private String tradeDetails;

    public TradeSignal(boolean shouldTrade, String tradeDetails) {
        this.shouldTrade = shouldTrade;
        this.tradeDetails = tradeDetails;
    }

    public boolean shouldTrade() {
        return shouldTrade;
    }

    public String getTradeDetails() {
        return tradeDetails;
    }
}
