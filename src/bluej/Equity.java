public abstract class Equity {
    static BookV1 book;
    String name;
    
    public Equity(BookV1 book, String name) {
        this.book = book;
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public static double getHighestBuyPrice() {
        return book.getHighestBuyPrice(getName());
    }

    public static double getLowestSellPrice() {
        return book.getLowestSellPrice(getName());
    }
}