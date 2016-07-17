public class VALEStrategy implements Strategy {
	VALEEquity valeEquity;
	VALBZEquity valbzEquity;

	public VALEStrategy(VALEEquity valeEquity, VALBZEquity valbzEquity) {
		this.valeEquity = valeEquity;
		this.valbzEquity = valbzEquity;
	}

	public Action determineAction() {
		double valbzFairValue = valbzEquity.getFairValue();
		double valeFairValue = valeEquity.getFairValue();

		if (valeEquity.getLowestSellPrice() < valeFairValue) {
			return Action.BUY;
		}
		if (valeEquity.getHighestBuyPrice() > valeFairValue) {
			return Action.SELL;
		}
		if (valbzEquity.getHighestBuyPrice() - 10 > valeFairValue) {
			return Action.CONVERT;
		}
		return Action.NONE;
	}
}