import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientV1 implements Client {
	private static final int SERVER_PORT = 20000;
	private static final String TEAM_NAME = "HOOLI";

	public final Socket s;
	public final PrintWriter out;
	public final BufferedReader in;

	private static int nextOrderId = 0;

	public ClientV1(String address) {
		s = new Socket(address, SERVER_PORT);
		out = new PrintWriter(s.getOutputStream());
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		out.println("HELLO " + TEAM_NAME);
	}

	public void shutdown() {
		s.close();
		out.close();
		in.close();
	}

	private static int nextID() {
		int toReturn = nextOrderId;
		nextOrderId++;

		return toReturn;
	}

	public Order buy(Equity e, int price, int size) {
		int order_id = nextID();
		String symbol = e.getName();

		//OrderV1 order = new OrderV1(order_id, Action.BUY, price, size);

		out.println("ADD " + order_id + " " + symbol + " BUY " + price + " " + size);
		return null;
	}

	public Order sell(Equity e, int price, int size) {
		int orderID = nextID();
		String symbol = e.getName();

		//OrderV1 order = new OrderV1(order_id, Action.SELL, price, size);

		out.println("ADD " + orderID + " " + symbol + " SELL " + price + " " + size);
		return null;
	}
	
	public Order cancel(Order o) {
		int orderID = o.getID();

		out.println("CANCEL " + orderID);
		return null;
	}

	public Order convert(Equity e, int size, String action) {
		int orderID = nextID();
		String symbol = e.getName();

		out.println("CONVERT " + orderID + " " + symbol + " " + action + " " + size);
		return null;
	}

	public static void main(String[] args) {
		String address = args[0];
		ClientV1 c = new ClientV1(address);

		//instantiate equitys & strategies
		BookV1 book = new BookV1();
		Parser p = new ParserV1(book);

		Equity bondEquity = new BONDEquity(book);
		Equity xlfEquity = new XLFEquity(book);
		Equity gsEquity = new GSEquity(book);
		Equity msEquity = new MSEquity(book);
		Equity wfcEquity = new WFCEquity(book);
		Equity valeEquity = new VALEEquity(book);
		Equity valbzEquity = new VALBZEquity(book);

		BONDStrategy bondStrategy = new BONDStrategy(bondEquity);
		VALEStrategy valeStrategy = new VALEStrategy(valeEquity, valbzEquity);
		VALBZStrategy valbzStrategy = new VALBZStrategy(valeEquity, valbzEquity);

		while (true) {
			//update book
			String s;
			while ((s = c.in.readLine()) != null) {
				p.parse(s);
			}	

			// determine actions
			Action bondAction = bondStrategy.determineAction();

			if (bondAction == Action.BUY) {
				int numToBuy = 100 - book.getPosition("BOND");
				c.buy(bondEquity, book.getLowestSellPrice("BOND"), numToBuy);
			}

			if (bondAction == Action.SELL) {
				int numToSell = -100 + book.getPosition("BOND");
				c.sell(bondEquity, book.getHighestBuyPrice("BOND"), numToSell);
			}
		}
	}
}
