public class XLFEquity extends Equity {
    BONDEquity bondEquity;
    GSEquity gsEquity;
    MSEquity msEquity;
    WFCEquity wfcEquity;
    
    public XLFEquity(BookV1 book, BONDEquity bondEquity, GSEquity gsEquity, MSEquity msEquity, WFCEquity wfcEquity) {
        super(book, "XLF");
        bondEquity = new BONDEquity(book);
        gsEquity = new GSEquity(book);
        msEquity = new MSEquity(book);
        wfcEquity = new WFCEquity(book);
    }
    
    public double getFairValue() {
    	return 3 * bondEquity.getFairValue() + 2 * gsEquity.getFairValue() + 3 * msEquity.getFairValue() + 2 * wfcEquity.getFairValue();
    }
}