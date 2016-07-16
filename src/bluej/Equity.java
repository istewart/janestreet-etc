public abstract class Equity {
    static BookV1 book;
    static String name;
    
    public Equity(BookV1 book, String name) {
        this.book = book;
        this.name = name;
    }
    
    public static String getName() {
        return name;
    }
    
    public static double getHighestBuyPrice() {
        return book.getHighestBuyPrice(getName());
    }

    public static double getLowestSellPrice() {
        return book.getLowestSellPrice(getName());
    }
}
