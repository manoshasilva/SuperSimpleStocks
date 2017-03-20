package stocks;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import stocks.calc.dividendyield.DividendYieldCalculator;

public class DividendYieldCalculatorTest {

	@Test
	public void calculateTest_CommonZeroDividend() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("TEA", StockType.COMMON, 0, new BigDecimal(0), 100);
		final BigDecimal dividendYield = dividendYieldCalculator.calculate(price, stock);
		Assert.assertEquals(0, dividendYield.doubleValue(), 0);
	}
	
	@Test
	public void calculateTest_CommonPositiveDividend() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("TEA", StockType.COMMON, 1, new BigDecimal(0), 100);
		final BigDecimal dividendYield = dividendYieldCalculator.calculate(price, stock);
		Assert.assertEquals(0.1, dividendYield.doubleValue(), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_CommonNegativeDividend() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("TEA", StockType.COMMON, -1, new BigDecimal(0), 100);
		dividendYieldCalculator.calculate(price, stock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_CommonZeroPrice() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(0);
		final Stock stock = new Stock("TEA", StockType.COMMON, 1, new BigDecimal(0), 100);
		dividendYieldCalculator.calculate(price, stock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_CommonNegativePrice() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(-1);
		final Stock stock = new Stock("TEA", StockType.COMMON, 1, new BigDecimal(0), 100);
		dividendYieldCalculator.calculate(price, stock);
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_CommonNullPrice() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = null;
		final Stock stock = new Stock("TEA", StockType.COMMON, 1, new BigDecimal(0), 100);
		dividendYieldCalculator.calculate(price, stock);
	}

	@Test
	public void calculateTest_PrefferedCorrectValues() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal(2), 100);
		final BigDecimal dividendYield = dividendYieldCalculator.calculate(price, stock);
		Assert.assertEquals(20, dividendYield.doubleValue(), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_PrefferedPriceNull() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = null;
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal(2), 100);
		dividendYieldCalculator.calculate(price, stock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_PrefferedPriceZero() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(0);
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal(2), 100);
		dividendYieldCalculator.calculate(price, stock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_PrefferedPriceNegative() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(-1);
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal(2), 100);
		dividendYieldCalculator.calculate(price, stock);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_PrefferedFixedDividendNull() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, null, 100);
		dividendYieldCalculator.calculate(price, stock);
	}
	
	@Test
	public void calculateTest_PrefferedFixedDividendZero() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal(0), 100);
		final BigDecimal dividendYield = dividendYieldCalculator.calculate(price, stock);
		Assert.assertEquals(0, dividendYield.doubleValue(), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_PrefferedFixedDividendNegative() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal(-2), 100);
		dividendYieldCalculator.calculate(price, stock);
	}
	
	@Test
	public void calculateTest_PrefferedParValueZero() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal(2), 0);
		final BigDecimal dividendYield = dividendYieldCalculator.calculate(price, stock);
		Assert.assertEquals(0, dividendYield.doubleValue(), 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void calculateTest_PrefferedParValueNegative() {
		final DividendYieldCalculator dividendYieldCalculator = new DividendYieldCalculator();
		final BigDecimal price = new BigDecimal(10);
		final Stock stock = new Stock("GIN", StockType.PREFERRED, 8, new BigDecimal(2), -100);
		dividendYieldCalculator.calculate(price, stock);
	}
	
}
