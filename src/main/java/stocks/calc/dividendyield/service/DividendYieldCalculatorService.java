package stocks.calc.dividendyield.service;

import java.math.BigDecimal;

import stocks.Stock;

public interface DividendYieldCalculatorService {

	BigDecimal calculate(final BigDecimal price, final Stock stock);

}
