import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

public class ClientV1 implements Client {
	private static final int SERVER_PORT = 20000;
	private static final String TEAM_NAME = "HOOLI";

	public Socket s;
	public PrintWriter out;
	public BufferedReader in;

	private static int nextOrderId = 0;

	public ClientV1(String address) throws Exception {
		System.out.println("Attempting to connect to " + address);

		s = null;
		out = null;
		in = null;

		try {
			s = new Socket(address, SERVER_PORT);
			out = new PrintWriter(s.getOutputStream());
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
		}

		out.println("HELLO " + TEAM_NAME);
		out.flush();

		System.out.println("Handshake sent!");
	}

	public void shutdown() throws Exception {
		s.close();
		out.close();
		in.close();
	}

	private static int nextID() {
		int toReturn = nextOrderId;
		nextOrderId++;

		return toReturn;
	}

	public int buy(Equity e, int price, int size) {
		int orderID = nextID();
		String symbol = e.getName();

		out.println("ADD " + orderID + " " + symbol + " BUY " + price + " " + size);
		return orderID;
	}

	public int sell(Equity e, int price, int size) {
		int orderID = nextID();
		String symbol = e.getName();

		out.println("ADD " + orderID + " " + symbol + " SELL " + price + " " + size);
		return orderID;
	}
	
	public int cancel(Order o) {
		int orderID = o.getId();

		out.println("CANCEL " + orderID);
		return orderID;
	}

	public int convert(Equity e, int size, String action) {
		int orderID = nextID();
		String symbol = e.getName();

		out.println("CONVERT " + orderID + " " + symbol + " " + action + " " + size);
		return orderID;
	}
    
    public static int calculatePortfolioValue(BookV1 book, BONDEquity bondEquity, GSEquity gsEquity, MSEquity msEquity, WFCEquity wfcEquity, XLFEquity xlfEquity, VALBZEquity valbzEquity, VALEEquity valeEquity) {
        int portfolioValue = 0;
        Map<String, Integer> ourSells = book.getOurSells();
        Map<String, Integer> positions = book.getPositions();
        
        for (String s : ourSells.keySet()) {
            if (s.equals("BOND")) {
                portfolioValue += bondEquity.getFairValue() * ourSells.get(s);
            }
            if (s.equals("GS")) {
                portfolioValue += gsEquity.getFairValue() * ourSells.get(s);
            }
            if (s.equals("MS")) {
                portfolioValue += msEquity.getFairValue() * ourSells.get(s);
            }
            if (s.equals("WFC")) {
                portfolioValue += wfcEquity.getFairValue() * ourSells.get(s);
            }
            if (s.equals("XLF")) {
                portfolioValue += xlfEquity.getFairValue() * ourSells.get(s);
            }
            if (s.equals("VALBZ")) {
                portfolioValue += valbzEquity.getFairValue() * ourSells.get(s);
            }
            if (s.equals("VALE")) {
                portfolioValue += valeEquity.getFairValue() * ourSells.get(s);
            }
        }
        
        for (String s : positions.keySet()) {
            if (s.equals("BOND")) {
                portfolioValue += bondEquity.getFairValue() * positions.get(s);
            }
            if (s.equals("GS")) {
                portfolioValue += gsEquity.getFairValue() * positions.get(s);
            }
            if (s.equals("MS")) {
                portfolioValue += msEquity.getFairValue() * positions.get(s);
            }
            if (s.equals("WFC")) {
                portfolioValue += wfcEquity.getFairValue() * positions.get(s);
            }
            if (s.equals("XLF")) {
                portfolioValue += xlfEquity.getFairValue() * positions.get(s);
            }
            if (s.equals("VALBZ")) {
                portfolioValue += valbzEquity.getFairValue() * positions.get(s);
            }
            if (s.equals("VALE")) {
                portfolioValue += valeEquity.getFairValue() * positions.get(s);
            }
        }
        
        return portfolioValue;
    }

	public static void main(String[] args) {
	    try {
			String address = args[0];
			ClientV1 c = new ClientV1(address);

			//instantiate equitys & strategies
			BookV1 book = new BookV1();
			Parser p = new ParserV1(book);

			System.out.println("Book and parser initialized.");

			BONDEquity bondEquity = new BONDEquity(book);
			GSEquity gsEquity = new GSEquity(book);
			MSEquity msEquity = new MSEquity(book);
			WFCEquity wfcEquity = new WFCEquity(book);
			XLFEquity xlfEquity = new XLFEquity(book, bondEquity, gsEquity, msEquity, wfcEquity);
			VALBZEquity valbzEquity = new VALBZEquity(book);
			VALEEquity valeEquity = new VALEEquity(book, valbzEquity);

			System.out.println("Equities initialized.");

			BONDStrategy bondStrategy = new BONDStrategy(bondEquity);
			VALEStrategy valeStrategy = new VALEStrategy(valeEquity, valbzEquity);
			VALBZStrategy valbzStrategy = new VALBZStrategy(valeEquity, valbzEquity);

			System.out.println("Strategies initialized.");

			while (true) {
				//update book
				String s = c.in.readLine();
				if (s == null) {
					System.out.println("Server closed.");
					return;
				}

				p.parse(s);

				// determine actions
				Action bondAction = bondStrategy.determineAction();
				Action valeAction = valeStrategy.determineAction();
				Action valbzAction  = valbzStrategy.determineAction(); 

                System.out.println("Bond Action: " + bondAction);
                System.out.println("Vale Action: " + valeAction);
                System.out.println("Valbz Action: " + valbzAction);

				//BOND

				if (bondAction == Action.BUY) {
					int offset = 0;
                    if (book.getOurBuys() != null && book.getOurBuys().get("BOND") != null) {
                        offset = book.getOurBuys().get("BOND");
                    }
					int numToBuy = 100 - book.getPosition("BOND") - offset;
					int orderID = c.buy(bondEquity, book.getLowestSellPrice("BOND"), numToBuy);
					book.add(orderID, "BOND", "BUY", book.getLowestSellPrice("BOND"), numToBuy);
				}

				if (bondAction == Action.SELL) {
                    int offset = 0;
                    if (book.getOurSells() != null && book.getOurSells().get("BOND") != null) {
                        offset = book.getOurSells().get("BOND");
                    }
					int numToSell = 100 + book.getPosition("BOND") - offset;
					int orderID = c.sell(bondEquity, book.getHighestBuyPrice("BOND"), numToSell);
					book.add(orderID, "BOND", "SELL", book.getHighestBuyPrice("BOND"), numToSell);
				}
                
				//VALE

				if (valeAction == Action.BUY) {
                    int offset = 0;
                    if (book.getOurBuys() != null && book.getOurBuys().get("VALE") != null) {
                        offset = book.getOurBuys().get("VALE");
                    }
					int numToBuy = 10 - book.getPosition("VALE") - offset;
					int orderID = c.buy(valeEquity, book.getLowestSellPrice("VALE"), numToBuy);
					book.add(orderID, "VALE", "BUY", book.getLowestSellPrice("VALE"), numToBuy);
				}

				if (valeAction == Action.SELL) {
                    int offset = 0;
                    if (book.getOurSells() != null && book.getOurSells().get("VALE") != null) {
                        offset = book.getOurSells().get("VALE");
                    }
					int numToSell = 10 + book.getPosition("VALE") - offset;
					int orderID = c.sell(valeEquity, book.getHighestBuyPrice("VALE"), numToSell);
					book.add(orderID, "VALE", "SELL", book.getHighestBuyPrice("VALE"), numToSell);
				}

				if (valeAction == Action.CONVERT) {
					int numToBuy = 10 + book.getPosition("VALE");
					int orderID = c.convert(valeEquity, numToBuy, "BUY");
				}

				//VALBZ

				if (valbzAction == Action.BUY) {
                    int offset = 0;
                    if (book.getOurBuys() != null && book.getOurBuys().get("VALBZ") != null) {
                        offset = book.getOurBuys().get("VALBZ");
                    }
					int numToBuy = 10 - book.getPosition("VALBZ") - offset;
					int orderID = c.buy(valbzEquity, book.getLowestSellPrice("VALBZ"), numToBuy);
					book.add(orderID, "VALBZ", "BUY", book.getLowestSellPrice("VALBZ"), numToBuy);
				}

				if (valbzAction == Action.SELL) {
                    int offset = 0;
                    if (book.getOurSells() != null && book.getOurSells().get("VALBZ") != null) {
                        offset = book.getOurSells().get("SELL");
                    }
                    int numToSell = 10 + book.getPosition("VALBZ") - offset;
					int orderID = c.sell(valbzEquity, book.getHighestBuyPrice("VALBZ"), numToSell);
					book.add(orderID, "VALBZ", "SELL", book.getHighestBuyPrice("VALBZ"), numToSell);
				}
                
				if (valbzAction == Action.CONVERT) {
					int numToBUY = 10 + book.getPosition("VALBZ");
					int orderID = c.convert(valbzEquity, numToBUY, "BUY");			
                }

				System.out.println("Cash on hand: " + book.getCash());
                System.out.println("Value of portfolio: " + calculatePortfolioValue(book, bondEquity, gsEquity, msEquity, wfcEquity, xlfEquity, valbzEquity, valeEquity));
			}
        } catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return;
        }
	}
}
