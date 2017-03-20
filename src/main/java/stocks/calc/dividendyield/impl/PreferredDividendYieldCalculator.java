package stocks.calc.dividendyield.impl;

import java.math.BigDecimal;

import stocks.Stock;
import stocks.calc.dividendyield.service.DividendYieldCalculatorService;

public class PreferredDividendYieldCalculator implements DividendYieldCalculatorService {

	public BigDecimal calculate(final BigDecimal price, final Stock stock) {
		final BigDecimal fixedDividend = stock.getFixedDividend();
		final Integer parValue = stock.getParValue();
		
		if(fixedDividend == null) {
			throw new IllegalArgumentException("Fixed dividend can not be null");
		}

		if (fixedDividend.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("Fixed dividend can not be negative. : " + stock.toString());
		}

		if (parValue < 0) {
			throw new IllegalArgumentException("Par Value can not be negative. : " + stock.toString());
		}

		final BigDecimal parValueBigDecimal = new BigDecimal(parValue);
		final BigDecimal multipleOfFixedDividendNParValue = fixedDividend.multiply(parValueBigDecimal);
		final BigDecimal dividendYield = multipleOfFixedDividendNParValue.divide(price);
		return dividendYield;
	}

}
