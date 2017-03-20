package stocks.calc.stockprice;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import stocks.trade.Trade;

public class VolumeWeightedStockPriceCalculator {

	private static final int TIME_PERIOD_IN_MINS = 15;

	public static BigDecimal calculate(final List<Trade> trades) {

		BigDecimal accumulatedTradedPriceNQuantity = new BigDecimal(0);
		int accumulatedQuantity = 0;

		if (trades != null && !trades.isEmpty()) {
			for (Trade trade : trades) {
				
				if (trade.getShareQuantity() < 0) {
					throw new IllegalArgumentException("Share quantity can not be negative");
				}
				
				final BigDecimal tradedPrice = trade.getTradedPrice();
				if(tradedPrice == null) {
					throw new IllegalArgumentException("Traded price can not be null");
				}
				
				if(tradedPrice.compareTo(BigDecimal.ZERO) < 0) {
					throw new IllegalArgumentException("Traded Price can not be negative");
				}
				
				final Timestamp tradedTime = trade.getTradeTime();
				if(tradedTime == null) {
					throw new IllegalArgumentException("Traded time can not be null");
				}
				
				final Timestamp fifteennMinsBefore = new Timestamp(System.currentTimeMillis() - TIME_PERIOD_IN_MINS * 60 * 1000);
				if(tradedTime.after(fifteennMinsBefore)) {
					final int shareQuantity = trade.getShareQuantity();
					final BigDecimal shareQuantityBigDecimal = new BigDecimal(shareQuantity);
					final BigDecimal multipliedTradedPriceNQuantity = tradedPrice
							.multiply(shareQuantityBigDecimal);
					accumulatedTradedPriceNQuantity = accumulatedTradedPriceNQuantity.add(multipliedTradedPriceNQuantity);
					accumulatedQuantity += shareQuantity;
				}
				
			}
		}

		if (accumulatedQuantity == 0) {
			throw new IllegalArgumentException("All the quantities can not be zero");
		}

		final BigDecimal accumulatedQuantityBigDecimal = new BigDecimal(accumulatedQuantity);
		final BigDecimal volumeWeightedStockPrice = accumulatedTradedPriceNQuantity
				.divide(accumulatedQuantityBigDecimal);
		return volumeWeightedStockPrice;
	}

}
