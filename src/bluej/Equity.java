public abstract class Equity {
    BookV1 book;
    String name;
    
    public Equity(BookV1 book, String name) {
        this.book = book;
        this.name = name;
    }
    
    public static String getName() {
        return name;
    }
    
    public double getHighestBuyPrice() {
        return book.getHighestBuyPrice(getName());
    }

    public double getLowestSellPrice() {
        return book.getLowestSellPrice(getName());
    }
}
