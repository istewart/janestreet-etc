public class ClientV1 implements Client {
	private static final int SERVER_PORT = 20000
	private static final String TEAM_NAME "HOOLI";

	public final Socket s;
	public final DataOutputStream out;
	public final BufferedReader in;

	private static final int nextOrderId = 0;

	public ClientV1(string address) {
		s = new Socket(address, SERVER_PORT);
		out = new PrintWriter(clientSocket.getOutputStream());
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));

		out.println("HELLO " + TEAM_NAME)
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
		int order_id = nextID()
		String symbol = e.getSymbol()

		out.println("ADD " + order_id + " " + symbol + " BUY " + price + " " + size);
	}

	public Order sell(Equity e, int price, int size) {
		int orderID = nextID()
		String symbol = e.getSymbol()

		out.println("ADD " + orderID + " " + symbol + " SELL " + price + " " + size);
	}
	
	public Order cancel(Order o) {
		int orderID = o.getID();

		out.println("CANCEL " + orderID);
	}

	public Order convert(Equity e, int size, String action) {
		int orderID = nextID()
		String symbol = e.getSymbol()

		out.println("CONVERT " + orderID + " " + symbol + " " + action + " " + size);
	}

	public static void main(String[] args) {
		String address = args[0];
		Client c = new ClientV1(address);

		//instantiate equitys & strategies
		BookV1 book = new BookV1();
		BONDEquity bondEquity = new BONDEquity(book);
		XLFEquity xlfEquity = new XLFEquity(book);
		GSEquity gsEquity = new GSEquity(book);
		MSEquity msEquity = new MSEquity(book);
		WFCEquity wfcEquity = new WFCEquity(book);
		VALEEquity valeEquity = new VALEEquity(book);
		VALBZEquity valbzEquity = new VALBZEquity(book);

		BONDStrategy bondStrategy = new bondStrategy(bondEquity);
		VALEStrategy valeStrategy = new valeStrategy(valeEquity, valbzEquity);
		VALBZStrategy valbzStrategy = new valbzStrategy(valeEquity, valbzEquity);

		while (true) {
			//update book


			// determine actions
			Action bondAction = bondStrategy.determineAction();

			if (bondAction == Action.BUY) {
				int numToBuy = 100 - book.getPosition("BOND");
				OrderV1 order = new OrderV1("BOND", Action.BUY, book.getLowestSellPrice("BOND"), numToBuy);
			}

			if (bondAction == Action.SELL) {
				int numToSell = -100 + book.getPosition("BOND");
				OrderV1 order = new OrderV1("BOND", Action.SELL, book.getHighestBuyPrice("BOND"), numToSell);
			}
		}
	}
}
