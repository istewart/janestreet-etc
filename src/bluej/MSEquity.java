public class MSEquity  extends Equity  {
	public static double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}
