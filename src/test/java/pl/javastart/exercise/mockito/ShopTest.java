package pl.javastart.exercise.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ShopTest {

    @Mock private ShopRepository shopRepository;
    private Shop shop;
    @InjectMocks private ShopController shopController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        Map<Item, Integer> stock = new HashMap<>();
        stock.put(new Item("Jogurt", 1, 2, true), 5);
        stock.put(new Item("Mleko", 1, 3, true), 1);
        shop = spy(new Shop(0, stock));
        when(shopRepository.findShop()).thenReturn(shop);
        shopController = new ShopController(shopRepository);
    }

    @Test
    public void soundTest(){
        // given
        Human human = new Human("Franio",45, "Górnik", 10);
        // when
        shopController.sellItem(human, "Jogurt");
        //then
        verify(shop).playCashSound();
    }

    @Test
    public void isDeletedMilk(){
        // given
        Human human = new Human("Franio",45, "Górnik", 10);
        // when
        shopController.sellItem(human, "Mleko");
        boolean answer = shop.hasItem("Mleko");
        //then
        assertThat(answer,is(false));
    }

    @Test
    public void shopMoney(){
        // given
        Human human = new Human("Franio",45, "Górnik", 10);
        // when
        shopController.sellItem(human, "Jogurt");
        int shopMoney = shop.getMoney();
        //then
        assertThat(shopMoney,is(2));
    }

    @Test
    public void howManyJoghurts(){
        // given
        Human human = new Human("Franio",45, "Górnik", 10);
        int number=0;

        // when
        shopController.sellItem(human, "Jogurt");
        Set<Map.Entry<Item, Integer>> set = shop.getStock().entrySet();
        for (Map.Entry<Item, Integer> mentry : set) {
            if (mentry.getKey().getName().equals("Jogurt")) {
                number = mentry.getValue();
                break;
            }
        }
        //then
        assertThat(number,is(4));
    }
}
