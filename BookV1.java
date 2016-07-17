import java.util.*;
import java.lang.*;

public class BookV1 implements Book {
	private int cash = 0;
	private Map<String, Integer> positions = new HashMap<String, Integer>();
    private Map<String, Integer> ourSells = new HashMap<String, Integer>();
    private Map<String, Integer> ourBuys = new HashMap<String, Integer>();
	private Map<String, List<Integer[]>> buy = new HashMap<String, List<Integer[]>>();
	private Map<String, List<Integer[]>> sell = new HashMap<String, List<Integer[]>>();
    private Map<Integer, OrderV1> orders = new HashMap<Integer, OrderV1>(); // order IDs as keys

	public BookV1() {
		positions.put("BOND", 0);
		positions.put("VALBZ", 0);
		positions.put("VALE", 0);
		positions.put("GS", 0);
		positions.put("MS", 0);
		positions.put("WFC", 0);
		positions.put("XLF", 0);
	}
    
    public void fill(int orderId, String s, String type, int price, int size) {
        if (type.equals("BUY")) {
            if (ourBuys.hasKey(s)) {
                ourBuys.put(s, ourBuys.get(s, 0) - size);
            } else {
                ourBuys.put(s, -size);
            }
            
            if (positions.hasKey(s)) {
                positions.put(s, positions.get(s, 0) + size);
            } else {
                positions.put(s, size);
            }
            
            cash -= price * size;
        } else if (type.equals("SELL")) {
            if (ourSells.hasKey(s)) {
                ourSells.put(s, ourSells.get(s, 0) - size);
            } else {
                ourSells.put(s, -size);
            }
            
            cash += price * size;
        }
    }
    
    public void ack(int orderId) {
        OrderV1 order = orders.get(orderId);
        String s = order.getName();
        int size = order.getAmount();
        
        if (type.equals("BUY")) {
            if (ourBuys.hasKey(s)) {
                ourBuys.put(s, ourBuys.get(s, 0) + size);
            } else {
                ourBuys.put(s, size);
            }
        } else if (type.equals("SELL")) {
            if (ourSells.hasKey(s)) {
                ourSells.put(s, ourSells.get(s, 0) + size);
            } else {
                ourSells.put(s, size);
            }
            
            if (positions.hasKey(s)) {
                positions.put(s, positions.get(s, 0) - size);
            } else {
                positions.put(s, -size);
            }
        }
    }
    
    public void reject(int orderId, String reason) {
        System.out.println("Order " + orderId + " rejected for reason: " + reason);
        orders.remove(orderId);
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
    
    public Map<String, Integer> getPositions() {
        return positions;
    }
    
    public Map<String, Integer> getOurSells() {
        return ourSells;
    }
    
    public Map<String, Integer> getOurBuys() {
        return ourBuys;
    }

	public void setCash(int cash) {
		this.cash = cash;
	}

	public int getCash() {
		return this.cash;
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

    public void add(int orderId, String symbol, String actionString, int price, int size) {
	Action action;
	if (actionString.equals("BUY")) {
		action = Action.BUY;
	}
	if (actionString.equals("SELL")) {
		action = Action.SELL;
	}
	if (actionString.equals("CONVERT")) {
		action = Action.CONVERT;
	}
        orders.put(orderId, new OrderV1(orderId, symbol, action, price, size));
    }

}
