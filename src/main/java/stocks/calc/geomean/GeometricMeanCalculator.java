package stocks.calc.geomean;

import java.math.BigDecimal;
import java.util.List;

import stocks.trade.Trade;

public class GeometricMeanCalculator {

	public static BigDecimal calculate(final List<Trade> trades) {

		int accumulatedShareQuantiy = 0;
		BigDecimal multipliedPrices = new BigDecimal(1);
		BigDecimal geometricMean = new BigDecimal(0);

		if (trades != null && !trades.isEmpty()) {
			for (Trade trade : trades) {
				final int shareQuantity = trade.getShareQuantity();
				if(shareQuantity < 0) {
					throw new IllegalArgumentException("Share quantity can not be negative");
				}
				
				accumulatedShareQuantiy += shareQuantity;

				final BigDecimal tradedPrice = trade.getTradedPrice();
				if(tradedPrice == null) {
					throw new IllegalArgumentException("Traded price can not be null");
				}
				
				if(tradedPrice.compareTo(BigDecimal.ZERO) < 0) {
					throw new IllegalArgumentException("Traded price can not be negative");
				}
				
				final BigDecimal multipliedPriceForTrade = tradedPrice.pow(shareQuantity);
				multipliedPrices = multipliedPrices.multiply(multipliedPriceForTrade);
			}
			
			if(accumulatedShareQuantiy == 0) {
				throw new IllegalArgumentException("All share quantities can not be zero");
			}

			final double multipliedPricesDouble = multipliedPrices.doubleValue();
			final double geometricMeanDouble = Math.pow(multipliedPricesDouble, 1.0 / accumulatedShareQuantiy);
			geometricMean = new BigDecimal(geometricMeanDouble);
		}
		return geometricMean;
	}

}
