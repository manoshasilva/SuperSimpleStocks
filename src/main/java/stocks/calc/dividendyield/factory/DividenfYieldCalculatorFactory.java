package stocks.calc.dividendyield.factory;

import stocks.StockType;
import stocks.calc.dividendyield.impl.CommonDividendYieldCalculator;
import stocks.calc.dividendyield.impl.PreferredDividendYieldCalculator;
import stocks.calc.dividendyield.service.DividendYieldCalculatorService;

public class DividenfYieldCalculatorFactory {

	public static DividendYieldCalculatorService getDividendYieldCalculator(final StockType stockType) {
		if (StockType.COMMON.equals(stockType)) {
			return new CommonDividendYieldCalculator();
		} else if (StockType.PREFERRED.equals(stockType)) {
			return new PreferredDividendYieldCalculator();
		}
		return null;
	}

}
