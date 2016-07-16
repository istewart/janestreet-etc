import java.util.*;

public class BookV1 implements Book {
	private Map<String, Integer> positions = new HashMap<String, Integer>();
	private Map<String, List<int[]>> buy = new HashMap<String, List<int[]>>();
	private Map<String, List<int[]>> sell = new HashMap<String, List<int[]>>();

	public BookV1() {
		positions.put("BOND", 0);
		positions.put("VALBZ", 0);
		positions.put("VALE", 0);
		positions.put("GS", 0);
		positions.put("MS", 0);
		positions.put("WFC", 0);
		positions.put("XLF", 0);
	}

	public void update(String s, int amount) {
		positions.put(s, amount);
	}

	public void updateBuy(String s, List<int[]> newBuy) {
		buy.put(s, newBuy);
	}

	public void updateSell(String s, List<int[]> newSell) {
		sell.put(s, newSell);
	}

	public int getPosition(String s) {
		return positions.get(s);
	}

	public int getHighestBuyPrice(String s) {
		return buy.get(s).get(0)[0];
	}

	public int getLowestSellPrice(String s) {
		return sell.get(s).get(0)[0];
	}
}