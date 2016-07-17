public class XLFEquity extends Equity {
    BONDEquity bondEquity;
    GSEquity gsEquity;
    MSEquity msEquity;
    WFCEquity wfcEquity;
    
    public XLFEquity(BookV1 book, BONDEquity bondEquity, GSEquity gsEquity, MSEquity msEquity, WFCEquity wfcEquity) {
        super(book, "XLF");
        bondEquity = this.bondEquity;
        gsEquity = this.gsEquity;
        msEquity = this.msEquity;
        wfcEquity = this.wfcEquity;
    }
    
    public double getFairValue() {
    	return 3 * bondEquity.getFairValue() + 2 * gsEquity.getFairValue() + 3 * msEquity.getFairValue() + 2 * wfcEquity.getFairValue();
    }
}