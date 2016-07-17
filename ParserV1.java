import java.util.ArrayList;
import java.util.List;
import java.lang.Integer;

public class ParserV1 implements Parser {
	private final BookV1 book;

	public ParserV1(BookV1 book) {
		this.book = book;
	}

	public void parse(String s) {
		String[] split = s.split(" ");

		switch (split[0]) {
			case "HELLO": parseHello(split); break;
			case "OPEN": parseOpen(split); break;
			case "CLOSE": parseClose(split); break;
			case "ERROR": parseError(split); break;
			case "BOOK": parseBook(split); break;
			case "TRADE": parseTrade(split); break;
			case "ACK": parseAck(split); break;
			case "REJECT": parseReject(split); break;
			case "FILL": parseFill(split); break;
			case "OUT": parseOut(split); break;
		}
	}

	public void parseHello(String[] split) {
		int cash = Integer.parseInt(split[1]);
		this.book.setCash(cash);

		for (int i = 2; i < split.length; i++) {
			String curr = split[i];
			String[] currSplit = curr.split(":");

			String symbol = split[0];
			int position = Integer.parseInt(split[1]);

			this.book.update(symbol, position);
		}
	}

	public void parseOpen(String[] split) {
		// TODO: NOTHING?
	}

	public void parseClose(String[] split) {
		// TODO: NOTHING?
	}

	public void parseError(String[] split) {
		String message = split[1];
		System.out.println("RECEIVED ERROR: " + message);
		//TODO?
	}

	public void parseBook(String[] split) {
		String symbol = split[1];
		boolean isBuy = true;

		List<Integer[]> buy = new ArrayList<Integer[]>();
		List<Integer[]> sell = new ArrayList<Integer[]>();

		for (int i = 2; i < split.length; i++) {
			String curr = split[i];
			if (curr.equals("BUY")) {
				isBuy = true;
			} else if (curr.equals("SELL")) {
				isBuy = false;
			} else {
				String[] currSplit = curr.split(":");

				int price = Integer.parseInt(currSplit[0]);
				int size = Integer.parseInt(currSplit[1]);

				if (isBuy) {
					Integer[] pair = {price, size};
					buy.add(pair);
				} else {
					Integer[] pair = {price, size};
					sell.add(pair);
				}
			}
		}

		this.book.updateBuy(symbol, buy);
		this.book.updateSell(symbol, sell);
	}

	public void parseTrade(String[] split) {
		String symbol = split[1];
		int price = Integer.parseInt(split[2]);
		int size = Integer.parseInt(split[3]);

		// this.book.trade(symbol, price, size);
	}

	public void parseAck(String[] split) {
		int orderID = Integer.parseInt(split[1]);
		this.book.ack(orderID);
	}

	public void parseReject(String[] split) {
		int orderID = Integer.parseInt(split[1]);
		String reason = split[2];

		System.out.println("ORDER " + orderID + "REJECTED: " + reason);
		this.book.reject(orderID, reason);
	}

	public void parseFill(String[] split) {
		int orderID = Integer.parseInt(split[1]);
		String symbol = split[2];
		String type = split[3];
		int price = Integer.parseInt(split[4]);
		int size = Integer.parseInt(split[5]);

		this.book.fill(orderID, symbol, type, price, size);
	}

	public void parseOut(String[] split) {
		int orderID = Integer.parseInt(split[1]);
		this.book.out(orderID);
	}
}
