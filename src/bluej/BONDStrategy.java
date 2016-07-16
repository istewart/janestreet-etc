public class BONDStrategy implements Strategy {
	BONDEquity equity;

	public BONDStrategy(BONDEquity equity) {
		this.equity = equity;	
	}

	public Action determineAction() {
		double fairValue = equity.getFairValue();

		if (equity.getLowestSellPrice() < fairValue) {
			return Action.BUY;
		}
		if (equity.getHighestBuyPrice() > fairValue) {
			return Action.SELL;
		}
		return Action.NONE;
	}
}