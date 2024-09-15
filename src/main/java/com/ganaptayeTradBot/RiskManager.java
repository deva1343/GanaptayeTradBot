package com.ganaptayeTradBot;

public class RiskManager {

    // Configuration parameters
    private static final double MAX_POSITION_SIZE_PERCENTAGE = 0.02; // 2% of account balance
    private static final double MAX_DAILY_DRAWDOWN_PERCENTAGE = 0.05; // 5% of account balance
    private static final double STOP_LOSS_PERCENTAGE = 0.02; // 2% stop-loss
    private static final double TAKE_PROFIT_MULTIPLIER = 2.0; // 2x stop-loss distance for take-profit

    // Instance variables
    private double accountBalance;
    private double dailyLoss;
    private double currentTradeAmount;

    public RiskManager(double initialAccountBalance) {
        this.accountBalance = initialAccountBalance;
        this.dailyLoss = 0.0;
        this.currentTradeAmount = 0.0;
    }

    // Validate if the trade meets the risk management criteria
    public boolean isTradeValid(String tradeSignal, double tradeAmount, double entryPrice) {
        if (tradeAmount > accountBalance * MAX_POSITION_SIZE_PERCENTAGE) {
            TradeLogger.logTrade("Trade amount exceeds maximum position size.");
            return false;
        }

        double stopLoss = calculateStopLoss(entryPrice);
        double takeProfit = calculateTakeProfit(entryPrice);

        // Example: Check if the trade amount exceeds the daily drawdown limit
        if (dailyLoss + (tradeAmount * STOP_LOSS_PERCENTAGE) > accountBalance * MAX_DAILY_DRAWDOWN_PERCENTAGE) {
            TradeLogger.logTrade("Daily drawdown limit exceeded.");
            return false;
        }

        currentTradeAmount = tradeAmount;
        return true;
    }

    // Update daily loss with the loss from the executed trade
    public void updateDailyLoss(double tradeLoss) {
        dailyLoss += tradeLoss;
        if (dailyLoss > accountBalance * MAX_DAILY_DRAWDOWN_PERCENTAGE) {
            TradeLogger.logTrade("Daily drawdown limit exceeded.");
            // Handle exceeding drawdown scenario (e.g., halt trading for the day)
        }
    }

    // Calculate stop-loss level
    public double calculateStopLoss(double entryPrice) {
        return entryPrice * (1 - STOP_LOSS_PERCENTAGE);
    }

    // Calculate take-profit level
    public double calculateTakeProfit(double entryPrice) {
        return entryPrice + (entryPrice * STOP_LOSS_PERCENTAGE * TAKE_PROFIT_MULTIPLIER);
    }

    // Update account balance (e.g., after a trade is executed)
    public void updateAccountBalance(double newBalance) {
        this.accountBalance = newBalance;
    }

    // Getters
    public double getCurrentTradeAmount() {
        return currentTradeAmount;
    }
}
