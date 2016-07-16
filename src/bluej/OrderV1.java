public class OrderV1 implements Order {
    int id;
    String name;
    Action action;
    double price;
    int amount;
    
    public OrderV1(int id, String name, Action action, double price, int amount) {
        this.id = id;
        this.name = name;
        this.action = action;
        this.price = price;
        this.amount = amount;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public Action getAction() {
        return action;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getAmount() {
        return amount;
    }
}