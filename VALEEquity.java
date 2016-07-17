public class VALEEquity extends Equity {
    VALBZEquity valbzEquity;
    
    public VALEEquity(BookV1 book, VALBZEquity valbzEquity) {
        super(book, "VALE");
        this.valbzEquity = valbzEquity;
    }
    
	public double getFairValue() {
		return valbzEquity.getFairValue();
	}
}

