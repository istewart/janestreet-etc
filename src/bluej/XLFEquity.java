public class XLFEquity extends Equity {
    public XLFEquity(BookV1 book) {
        super(book, "XLF");
    }
    
    public static double getFairValue() {
    	return 3 * BONDEquity.getFairValue() + 2 * GSEquity.getFairValue() + 3 * MSEquity.getFairValue() + 2 * WFCEquity.getFairValue();
    }
}