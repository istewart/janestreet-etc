public interface Client {
	public int buy(Equity e, int price, int amount);
	public int sell(Equity e, int price, int amount);
	public int cancel(Order o);
	public int convert(Equity e, int size, String action);
	public void shutdown() throws Exception;
}
