package stocks;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import stocks.calc.stockprice.VolumeWeightedStockPriceCalculator;
import stocks.trade.Trade;
import stocks.trade.TradeType;
import stocks.trade.controller.TradeController;

public class VolumeWeightedStockPriceCalcTest {

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
				new BigDecimal(111), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		final BigDecimal volumeWeightedStockPirce = VolumeWeightedStockPriceCalculator
				.calculate(tradeController.getTrades());
		Assert.assertEquals(110.02, volumeWeightedStockPirce.doubleValue(), 0);
	}

	@Test
	public void calculateQuantityZero() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 0, TradeType.BUY, new BigDecimal(109),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(111), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		final BigDecimal volumeWeightedStockPirce = VolumeWeightedStockPriceCalculator
				.calculate(tradeController.getTrades());
		Assert.assertEquals(111, volumeWeightedStockPirce.doubleValue(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateQuantityZero2() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 0, TradeType.BUY, new BigDecimal(109),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 0, TradeType.BUY, new BigDecimal(111),
				"TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		VolumeWeightedStockPriceCalculator.calculate(tradeController.getTrades());
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateQuantityNegative() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), -1, TradeType.BUY,
				new BigDecimal(109), "TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(111), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		VolumeWeightedStockPriceCalculator.calculate(tradeController.getTrades());
	}

	@Test
	public void calculateTradedPriceZero() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY, new BigDecimal(0),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(111), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		final BigDecimal volumeWeightedStockPirce = VolumeWeightedStockPriceCalculator
				.calculate(tradeController.getTrades());
		Assert.assertEquals(56.61, volumeWeightedStockPirce.doubleValue(), 0);
	}

	@Test
	public void calculateTradedPriceZero2() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY, new BigDecimal(0),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY, new BigDecimal(0),
				"TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		final BigDecimal volumeWeightedStockPirce = VolumeWeightedStockPriceCalculator
				.calculate(tradeController.getTrades());
		Assert.assertEquals(0, volumeWeightedStockPirce.doubleValue(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateTradedPriceNegative() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY, new BigDecimal(-1),
				"TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(111), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		VolumeWeightedStockPriceCalculator.calculate(tradeController.getTrades());
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateTradedPriceNull() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis()), 49, TradeType.BUY, null, "TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(111), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		VolumeWeightedStockPriceCalculator.calculate(tradeController.getTrades());
	}

	@Test
	public void calculate15MinsBefore() {
		final Trade trade1 = new Trade(new Timestamp(System.currentTimeMillis() - 16 * 60 * 1000), 49, TradeType.BUY,
				new BigDecimal(109), "TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(111), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		final BigDecimal volumeWeightedStockPirce = VolumeWeightedStockPriceCalculator
				.calculate(tradeController.getTrades());
		Assert.assertEquals(111, volumeWeightedStockPirce.doubleValue(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void calculateTimeNull() {
		final Trade trade1 = new Trade(null, 49, TradeType.BUY, new BigDecimal(109), "TEA");
		final Trade trade2 = new Trade(new Timestamp(System.currentTimeMillis()), 51, TradeType.BUY,
				new BigDecimal(111), "TEA");
		tradeController.recordTrade(trade1);
		tradeController.recordTrade(trade2);
		VolumeWeightedStockPriceCalculator.calculate(tradeController.getTrades());
	}

}
