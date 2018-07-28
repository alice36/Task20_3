package pl.javastart.exercise.mockito;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Shop {

    private int money;
    private Map<Item, Integer> stock;

    public Shop(int money, Map<Item, Integer> stock) {
        this.money = money;
        this.stock = stock;
    }

    void playCashSound() {
        /* zakładamy, że ta metoda odtwarza dźwięk https://www.youtube.com/watch?v=Wj_OmtqVLxY, nie musimy jej implementować,
        sprawdzamy tylko czy została uruchomiona */
    }

    public boolean hasItem(String itemName) { //test
        // TODO dodaj kod sprawdzający czy sklep na w asortymencie przedmot o danej nazwie
        boolean check = false;
        Set<Map.Entry<Item, Integer>> set = stock.entrySet();
        for (Map.Entry<Item, Integer> mentry : set) {
            if (mentry.getKey().getName().equals(itemName)) {
                return true;
            } else {
                check = false;
            }
        }
        return check;
    }
    
    public Item findItemByName(String itemName) {//test
        // TODO dodaj kod wyszukujący przedmiot po jego nazwie
        Set<Map.Entry<Item, Integer>> set = stock.entrySet();
        Item item = null;

        for (Map.Entry<Item, Integer> mentry : set) {
            if(mentry.getKey().getName().equals(itemName)){
                item = mentry.getKey();
            }
        }
        return item;
    }

    public void deleteItem(String itemName){//test
        Set<Map.Entry<Item, Integer>> set = stock.entrySet();
        Item item = null;

        for (Map.Entry<Item, Integer> mentry : set) {
            if(mentry.getKey().getName().equals(itemName)){
               stock.remove(mentry.getKey());
               break;
            }
        }
    }

    public void minusItem(String itemName, int amount) {//test
        Set<Map.Entry<Item, Integer>> set = stock.entrySet();
        Item item = null;

        for (Map.Entry<Item, Integer> mentry : set) {
            if (mentry.getKey().getName().equals(itemName)) {
                if (mentry.getValue() - amount <= 0) {
                    deleteItem(itemName);
                    break;
                } else {
                    stock.put(mentry.getKey(), mentry.getValue() - amount);
                }
            }
        }
    }

    public int getMoney() {
        return money;
    }

    public Map<Item, Integer> getStock() {
        return stock;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
