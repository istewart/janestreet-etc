import java.util.*;
import java.lang.*;

public class BookV1 implements Book {
	private int cash = 0;
	private Map<String, Integer> positions = new HashMap<String, Integer>();
	private Map<String, List<Integer[]>> buy = new HashMap<String, List<Integer[]>>();
	private Map<String, List<Integer[]>> sell = new HashMap<String, List<Integer[]>>();

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

	public void updateBuy(String s, List<Integer[]> newBuy) {
		buy.put(s, newBuy);
	}

	public void updateSell(String s, List<Integer[]> newSell) {
		sell.put(s, newSell);
	}

	public int getPosition(String s) {
		return positions.get(s);
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getHighestBuyPrice(String s) {
        if (buy.get(s) == null || buy.get(s).size() == 0) {
            return Integer.MIN_VALUE;
        }
        
		return buy.get(s).get(0)[0];
	}

	public int getLowestSellPrice(String s) {
        if (sell.get(s) == null || sell.get(s).size() == 0) {
            return Integer.MAX_VALUE;
        }
        
		return sell.get(s).get(0)[0];
	}
}
