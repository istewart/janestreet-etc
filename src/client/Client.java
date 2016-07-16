public interface Client {
	public Order buy(Equity e, int price, int amount);
	public Order sell(Equity e, int price, int amount);
	public Order cancel(Order o);
	public Order convert(Equity e, int size, String action);
	public void shutdown() throws Exception;
}
