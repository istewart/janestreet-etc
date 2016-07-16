public class ParserV1 implements Parser {
	private final BufferedReader in;

	public ParserV1(BufferedReader in) {
		this.in = in;
	}

	public void parse() {
		String s = in.readLine().trim();
		String[] split = s.split();

		switch (split[0]) {
			case "HELLO": parseHello(split);
			case "OPEN": parseOpen(split);
			case "CLOSE": parseClose(split);
			case "ERROR": parseError(split);
			case "BOOK": parseBook(split);
			case "TRADE": parseTrade(split);
			case "ACK": parseAck(split);
			case "REJECT": parseReject(split);
			case "FILL": parseFill(split);
			case "OUT": parseOut(split);
		}
	}

	public void parseHello(String[] split) {
		int cash = Integer.parseInt(split[0]);
		book.setCash(cash);

		for (int i = 2; i < split.length; i++) {
			String curr = split[i];
			String[] currSplit = curr.split(":");

			String symbol = split[0];
			int position = Integer.parseInt(split[1]);

			book.setEquity(symbol, position);
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
		for (int i = 1; i < split.size; i++) {
			String curr = split[i];
			if ()
		}
	}
}