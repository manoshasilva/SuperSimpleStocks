package stocks.calc.peratio;

import java.math.BigDecimal;

public class PERatioCalculator {

	public static BigDecimal caluclate(final BigDecimal price, final int dividend) {

		if (price == null) {
			throw new IllegalArgumentException("Price can not be null");
		}

		if (price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Price can not be negative. Price : " + price);
		}

		if (dividend <= 0) {
			throw new IllegalArgumentException("Dividend can not be zero or null. Dividend : " + dividend);
		}

		final BigDecimal dividendBigDecimal = new BigDecimal(dividend);
		final BigDecimal peRatio = price.divide(dividendBigDecimal);
		return peRatio;

	}

}
