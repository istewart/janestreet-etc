public class WFCEquity extends Equity {
    public WFCEquity(BookV1 book) {
        super(book, "WFC");
    }
    
	public double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}
