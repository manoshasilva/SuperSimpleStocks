package stocks.calc.dividendyield;

import java.math.BigDecimal;

import stocks.Stock;
import stocks.StockType;
import stocks.calc.dividendyield.factory.DividenfYieldCalculatorFactory;
import stocks.calc.dividendyield.service.DividendYieldCalculatorService;

public class DividendYieldCalculator {

	public BigDecimal calculate(final BigDecimal price, final Stock stock) {
		if (stock == null) {
			throw new IllegalArgumentException("Stock is null.");
		}

		final StockType stockType = stock.getStockType();
		if (stockType == null) {
			throw new IllegalArgumentException("Stock should have a stock type : " + stock.toString());
		}
		
		if(price == null) {
			throw new IllegalArgumentException("Price can not be null");
		}

		if (price.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("Price can not be zero or negative. : " + stock.toString());
		}

		final DividendYieldCalculatorService dividendYieldCalulator = DividenfYieldCalculatorFactory
				.getDividendYieldCalculator(stockType);
		final BigDecimal dividendYield = dividendYieldCalulator.calculate(price, stock);
		return dividendYield;
	}

}
