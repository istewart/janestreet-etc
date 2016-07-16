public interface Strategy {
	public enum Action { BUY, SELL, NONE }
}

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


	public class VALEStrategy(VALEEquity valeEquity, VALBZ valbzEquity) {

	}
}