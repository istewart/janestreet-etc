public class MSEquity extends Equity  {
    public MSEquity(BookV1 book) {
        super(book, "MS");
    }
    
	public double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}

