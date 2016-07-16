import java.io.BufferedReader;
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
		String symbol = e.getSymbol();

		out.println("ADD " + order_id + " " + symbol + " BUY " + price + " " + size);
	}

	public Order sell(Equity e, int price, int size) {
		int orderID = nextID();
		String symbol = e.getSymbol();

		out.println("ADD " + orderID + " " + symbol + " SELL " + price + " " + size);
	}
	
	public Order cancel(Order o) {
		int orderID = o.getID();

		out.println("CANCEL " + orderID);
	}

	public Order convert(Equity e, int size, String action) {
		int orderID = nextID();
		String symbol = e.getSymbol();

		out.println("CONVERT " + orderID + " " + symbol + " " + action + " " + size);
	}

	public static void main(String[] args) {
		String address = args[0];
		Client c = new ClientV1(address);
		// TODO
	}
}
