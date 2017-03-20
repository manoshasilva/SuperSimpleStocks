package stocks.trade.controller;

import java.util.List;

import stocks.trade.Trade;
import stocks.trade.data.TradeHolder;

public class TradeController {
	
	public void recordTrade(final Trade trade) {
		final TradeHolder tradeHolder = TradeHolder.getInstance();
		tradeHolder.addTrade(trade);
	}
	
	public List<Trade> getTrades() {
		final TradeHolder tradeHolder = TradeHolder.getInstance();
		return tradeHolder.getTrades();
	}
	
	public void clearAllTrades() {
		final TradeHolder tradeHolder = TradeHolder.getInstance();
		tradeHolder.clearAllTrades();
	}

}
