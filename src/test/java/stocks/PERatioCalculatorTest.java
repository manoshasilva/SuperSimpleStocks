package stocks;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import stocks.calc.peratio.PERatioCalculator;

public class PERatioCalculatorTest {

	@Test
	public void calculate() {
		final BigDecimal price = new BigDecimal(100);
		final int dividend = 10;
		final BigDecimal peRatio = PERatioCalculator.caluclate(price, dividend);
		Assert.assertEquals(10, peRatio.doubleValue(), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculatePriceNull() {
		final BigDecimal price = null;
		final int dividend = 10;
		PERatioCalculator.caluclate(price, dividend);
	}
	
	@Test
	public void calculatePriceZero() {
		final BigDecimal price = new BigDecimal(0);
		final int dividend = 10;
		final BigDecimal peRatio = PERatioCalculator.caluclate(price, dividend);
		Assert.assertEquals(0, peRatio.doubleValue(), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculatePriceNegative() {
		final BigDecimal price = null;
		final int dividend = 10;
		PERatioCalculator.caluclate(price, dividend);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateDividendZero() {
		final BigDecimal price = new BigDecimal(100);
		final int dividend = 0;
		PERatioCalculator.caluclate(price, dividend);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateDividendNegative() {
		final BigDecimal price = new BigDecimal(100);
		final int dividend = -10;
		PERatioCalculator.caluclate(price, dividend);
	}

}
