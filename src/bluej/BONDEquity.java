public class BONDEquity extends Equity {
    public BONDEquity(BookV1 book) {
        super(book, "BOND");
    }
    
    public static double getFairValue() {
        return 1000;
    }
}

