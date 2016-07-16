public class VALEEquity extends Equity {
    public VALEEquity(BookV1 book) {
        super(book, "VALE");
    }
    
	public static double getFairValue() {
		return VALBZEquity.getFairValue();
	}
}

