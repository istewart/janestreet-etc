public class VALBZStrategy implements Strategy {
	VALEEquity valeEquity;
	VALBZEquity valbzEquity;

	public VALBZStrategy(VALEEquity valeEquity, VALBZEquity valbzEquity) {
		this.valeEquity = valeEquity;
		this.valbzEquity = valbzEquity;
	}

	public Action determineAction() {
		double valbzFairValue = valbzEquity.getFairValue();
		double valeFairValue = valeEquity.getFairValue();

		if (valbzEquity.getLowestSellPrice() < valbzFairValue) {
			return Action.BUY;
		}
		if (valbzEquity.getHighestBuyPrice() > valbzFairValue) {
			return Action.SELL;
		}
		if (valeEquity.getHighestBuyPrice() - 10 > valbzFairValue) {
			return Action.CONVERT;
		}
		return Action.NONE;
	}
}