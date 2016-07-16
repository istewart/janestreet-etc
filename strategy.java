

public class BONDStrategy implements Strategy {
	BONDEquity equity;

	public BONDStrategy(BONDEquity equity) {
		this.equity = equity;	
	}

	public static Action determineAction() {
		double fairValue = equity.getFairValue();

		if (equity.getLowestSellPrice() < fairValue) {
			return BUY;
		}
		if (equity.getHighestBuyPrice() > fairValue) {
			return SELL;
		}
		return NONE;
	}
}

public class VALEStrategy implements Strategy {
	VALEEquity valeEquity;
	VALBZEquity valbzEquity;

	public VALEStrategy(VALEEquity valeEquity, VALBZ valbzEquity) {
		this.valeEquity = valeEquity;
		this.valbzEquity = valbzEquity;
	}

	public static Action determineAction() {
		double valbzFairValue = valbzEquity.getFairValue();
		double valeFairValue = valeEquity.getFairValue();

		if (valeEquity.getLowestSellPrice() < valeFairValue) {
			return BUY;
		}
		if (valeEquity.getHighestBuyPrice() > valeFairValue) {
			return SELL;
		}
		if (valbzEquity.getHighestBuyPrice() - 10 > valeFairValue) {
			return CONVERT;
		}
		return NONE;
	}
}

public class VALBZStrategy implements Strategy {
	VALEEquity valeEquity;
	VALBZEquity valbzEquity;

	public VALBZStrategy(VALEEquity valeEquity, VALBZ valbzEquity) {
		this.valeEquity = valeEquity;
		this.valbzEquity = valbzEquity;
	}

	public static Action determineAction() {
		double valbzFairValue = valbzEquity.getFairValue();
		double valeFairValue = valeEquity.getFairValue();

		if (valbzEquity.getLowestSellPrice() < valbzFairValue) {
			return BUY;
		}
		if (valbzEquity.getHighestBuyPrice() > valbzFairValue) {
			return SELL;
		}
		if (valeEquity.getHighestBuyPrice() - 10 > valbzFairValue) {
			return CONVERT;
		}
		return NONE;
	}
}