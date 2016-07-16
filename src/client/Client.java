public interface Client {
	public Order buy(Equity e, int amount);
	public Order sell(Equity e, int amount);
	public Order cancel(Order o);
	public Order convert(Equity e);
}