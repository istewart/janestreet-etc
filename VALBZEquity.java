public class VALBZEquity extends Equity {
    public VALBZEquity(BookV1 book) {
        super(book, "VALBZ");
    }
    
	public double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}