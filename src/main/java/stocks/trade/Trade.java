package stocks.trade;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Trade {

	private Timestamp tradeTime;
	private int shareQuantity;
	private TradeType buyOrSellIndicator;
	private BigDecimal tradedPrice;
	private String stockSymbol;

	public Trade() {

	}

	public Trade(final Timestamp tradeTime, final int shareQuantity, final TradeType buyOrSellIndicator,
			final BigDecimal tradedPrice, final String stockSymbol) {
		this.tradeTime = tradeTime;
		this.shareQuantity = shareQuantity;
		this.buyOrSellIndicator = buyOrSellIndicator;
		this.tradedPrice = tradedPrice;
		this.stockSymbol = stockSymbol;
	}

	public Timestamp getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Timestamp tradeTime) {
		this.tradeTime = tradeTime;
	}

	public int getShareQuantity() {
		return shareQuantity;
	}

	public void setShareQuantity(Integer shareQuantity) {
		this.shareQuantity = shareQuantity;
	}

	public TradeType getBuyOrSellIndicator() {
		return buyOrSellIndicator;
	}

	public void setBuyOrSellIndicator(TradeType buyOrSellIndicator) {
		this.buyOrSellIndicator = buyOrSellIndicator;
	}

	public BigDecimal getTradedPrice() {
		return tradedPrice;
	}

	public void setTradedPrice(BigDecimal tradedPrice) {
		this.tradedPrice = tradedPrice;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

}
