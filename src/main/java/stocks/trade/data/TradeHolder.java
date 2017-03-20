package stocks.trade.data;

import java.util.ArrayList;
import java.util.List;

import stocks.trade.Trade;

public class TradeHolder {
	
	private List<Trade> trades;
	
	public List<Trade> getTrades() {
		if(trades == null) {
			trades = new ArrayList<>();
		}
		return trades;
	}
	
	public void addTrade(final Trade trade) {
		if(trades == null) {
			trades = new ArrayList<>();
		}
		trades.add(trade);
	}
	
	public void clearAllTrades() {
		if(trades != null) {
			trades.clear();
		}
	}

	private TradeHolder() {
		
	}
	
	public static TradeHolder getInstance() {
		return SingletonHolder.SINGLETON;
	}
	
	private static class SingletonHolder {
		private static TradeHolder SINGLETON = new TradeHolder();
	}

}
