public interface Equity {
	public double getFairValue();

	public static double getHighestBuyPrice() {
		return 0; // TODO
	}

	public static double getLowestSellPrice() {
		return 0; // TODO
	}
}

public class BONDEquity implements Equity { 
	public static double getFairValue() {
		return 1000;
	}
}

public class XLFEquity implements Equity {
	public static double getFairValue() {
		return 3 * BONDEquity.getFairValue() + 2 * GSEquity.getFairValue() + 3 * MSEquity.getFairValue() + 2 * WFCEquity.getFairValue();
	}
}

public class GSEquity implements Equity {
	public static double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}

public class MSEquity implements Equity {
	public static double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}

public class WFCEquity implements Equity {
	public static double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}

public class VALEEquity implements Equity {
	public static double getFairValue() {
		return VALBZEquity.getFairValue();
	}
}

public class VALBZEquity implements Equity {
	public static double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}