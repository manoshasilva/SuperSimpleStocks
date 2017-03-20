package stocks;

import java.math.BigDecimal;

public class Stock {

	private String stockSymbol;
	private StockType stockType;
	private int dividend;
	private BigDecimal fixedDividend;
	private int parValue;

	public Stock() {

	}

	public Stock(final String stockSymbol, final StockType stockType, final int dividend, final BigDecimal fixedDividend,
			final int parValue) {
		this.stockSymbol = stockSymbol;
		this.stockType = stockType;
		this.dividend = dividend;
		this.fixedDividend = fixedDividend;
		this.parValue = parValue;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public StockType getStockType() {
		return stockType;
	}

	public void setStockType(StockType stockType) {
		this.stockType = stockType;
	}

	public int getDividend() {
		return dividend;
	}

	public void setDividend(Integer dividend) {
		this.dividend = dividend;
	}

	public BigDecimal getFixedDividend() {
		return fixedDividend;
	}

	public void setFixedDividend(BigDecimal fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public int getParValue() {
		return parValue;
	}

	public void setParValue(Integer parValue) {
		this.parValue = parValue;
	}

	@Override
	public String toString() {
		return "Stock [stockSymbol=" + stockSymbol + ", stockType=" + stockType + ", dividend=" + dividend
				+ ", fixedDividend=" + fixedDividend + ", parValue=" + parValue + "]";
	}

}
