package com.ganaptayeTradBot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TradeLogger {
    private static final Logger logger = LogManager.getLogger(TradeLogger.class);

    public static void logTrade(String tradeDetails) {
        logger.info("Trade executed: " + tradeDetails);
    }

    public static void logError(String error) {
        logger.error("Error occurred: " + error);
    }
}
