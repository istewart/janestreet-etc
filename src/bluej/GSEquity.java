public class GSEquity extends Equity {
    public GSEquity(BookV1 book) {
        super(book, "GS");
    }
    
	public static double getFairValue() {
		return 0.5 * (getLowestSellPrice() + getHighestBuyPrice());
	}
}
