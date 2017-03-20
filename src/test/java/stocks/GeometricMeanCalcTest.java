package stocks;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import stocks.calc.geomean.GeometricMeanCalculator;
import stocks.trade.Trade;
import stocks.trade.TradeType;
import stocks.trade.controller.TradeController;

public class GeometricMeanCalcTest {

	TradeController tradeController;

	@Before
	public void setup() {
		tradeController = new TradeController();
		tradeController.clearAllTrades();
	}

	@Test
	public void calculate() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY,
				new BigDecimal(109), "TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(109), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		BigDecimal geometricMean = GeometricMeanCalculator.calculate(tradeController.getTrades());
		geometricMean = geometricMean.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(109, geometricMean.doubleValue(), 0);
	}

	@Test
	public void calculateQuantityZero() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 0, TradeType.BUY, new BigDecimal(109),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(109), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		BigDecimal geometricMean = GeometricMeanCalculator.calculate(tradeController.getTrades());
		geometricMean = geometricMean.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(109, geometricMean.doubleValue(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateQuantityZero2() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 0, TradeType.BUY, new BigDecimal(109),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 0, TradeType.BUY, new BigDecimal(109),
				"TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		GeometricMeanCalculator.calculate(tradeController.getTrades());
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateQuantityNegative() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), -1, TradeType.BUY,
				new BigDecimal(109), "TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(109), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		GeometricMeanCalculator.calculate(tradeController.getTrades());
	}

	@Test
	public void calculatePriceZero() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY, new BigDecimal(0),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(109), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		BigDecimal geometricMean = GeometricMeanCalculator.calculate(tradeController.getTrades());
		geometricMean = geometricMean.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(0, geometricMean.doubleValue(), 0);
	}

	@Test
	public void calculatePriceZero2() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY, new BigDecimal(0),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY, new BigDecimal(0),
				"TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		BigDecimal geometricMean = GeometricMeanCalculator.calculate(tradeController.getTrades());
		geometricMean = geometricMean.setScale(2, RoundingMode.HALF_UP);
		Assert.assertEquals(0, geometricMean.doubleValue(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculatePriceNegative() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY, new BigDecimal(-1),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(109), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		GeometricMeanCalculator.calculate(tradeController.getTrades());
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculatePriceNull() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY, null, "TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(109), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		GeometricMeanCalculator.calculate(tradeController.getTrades());
	}

}
