public class VALEEquity extends Equity {
    VALBZEquity valbzEquity;
    
    public VALEEquity(BookV1 book, VALBZEquity valbzEquity) {
        valbzEquity = new VALBZEquity(book);
        super(book, "VALE");
    }
    
	public double getFairValue() {
		return valbzEquity.getFairValue();
	}
}

