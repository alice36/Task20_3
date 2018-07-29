package pl.javastart.exercise.mockito;

public class ShopController {

    private Shop shop;

    public ShopController(ShopRepository shopRepository) {
        shop = shopRepository.findShop();
    }

    public void sellItem(Human human, String itemName) {

        if (shop.hasItem(itemName)) {
            Item item = shop.findItemByName(itemName);
            if (item.getAgeRestriction() > human.getAge()) {
                throw new TooYoungException();
            } else if (!ifEnoughMoney(human,itemName)){
                System.out.println("Not enough money");
            } else if (!ifIsLegal(human, itemName)){
                System.out.println("Not legal product");
            } else {
                shop.minusItem(itemName, 1);
                human.setMoney(Math.max(human.getMoney() - item.getPrice(), 0));
                shop.playCashSound();
                shop.setMoney(shop.getMoney() + item.getPrice());
            }

        } else {
            // TODO sklep nie ma danego przedmiotu, wyrzuć wyjątek OutOfStockException
            throw new OutOfStockException();
        }

    }

    public boolean ifEnoughMoney(Human human, String itemName) {
        boolean check = false;

        Item item = shop.findItemByName(itemName);
        if (item.getPrice() > human.getMoney()) {
            return false;
        } else {
            check = true;
        }
        return check;
    }

    public boolean ifIsLegal(Human human, String itemName) {
        boolean check = false;

        Item item = shop.findItemByName(itemName);
        if (!item.isLegal() && (human.getJob().equals("Policjant"))) {
            check = false;
        } else {
            check = true;
        }
        return check;
    }


}
