package stocks.calc.dividendyield.impl;

import java.math.BigDecimal;

import stocks.Stock;
import stocks.calc.dividendyield.service.DividendYieldCalculatorService;

public class CommonDividendYieldCalculator implements DividendYieldCalculatorService {

	public BigDecimal calculate(final BigDecimal price, final Stock stock) {
		final int dividend = stock.getDividend();

		if (dividend < 0) {
			throw new IllegalArgumentException("Dividend can not be negative. : " + stock.toString());
		}

		final BigDecimal dividednBigDecimal = new BigDecimal(dividend);
		final BigDecimal divedendYield = dividednBigDecimal.divide(price);
		return divedendYield;
	}

}
