package pl.javastart.exercise.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ShopControllerTest {

    @Mock private ShopRepository shopRepository;
    private Shop shop;
    @InjectMocks private ShopController shopController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        Map<Item, Integer> stock = new HashMap<>();
        stock.put(new Item("Piwo", 18, 4, true), 5);
        stock.put(new Item("Marihuana", 18, 100, false), 5);
        stock.put(new Item("Chleb", 1, 5, true), 5);
        stock.put(new Item("Jogurt", 1, 2, true), 5);
        stock.put(new Item("Mleko", 1, 3, true), 1);

        shop = new Shop(0, stock);
        when(shopRepository.findShop()).thenReturn(shop);

        shopController = new ShopController(shopRepository);
    }

    @Test(expected = TooYoungException.class)
    public void shouldNotSellBeerToYoung() {
        // given
        Human human1 = new Human("Jan",14, "Uczeń", 10);
        // when
        shopController.sellItem(human1, "Piwo");
    }

    @Test(expected = OutOfStockException.class)
    public void outOfStock() {
        // given
        Human human = new Human("Zosia", 16, "Kasjer", 10);
        // when
        shopController.sellItem(human, "Kiełbasa");
    }

    @Test
    public void shouldNotSellMarihuana(){
        // given
        Human human = new Human("Zenek",25, "Policjant", 100);
        // when
        boolean answer = shopController.ifIsLegal(human, "Marihuana");
        //then
        assertThat(answer, is(false));
    }

    @Test
    public void notEnoughMoney(){
        // given
        Human human = new Human("Benek",25, "Ksiegowy", 4);
        // when
        boolean answer = shopController.ifEnoughMoney(human, "Chleb");
        //then
        assertThat(answer, is(false));
    }


    @Test
    public void humanMoney(){
        // given
        Human human = new Human("Franio",45, "Górnik", 10);
        // when
        shopController.sellItem(human, "Jogurt");
        int humanMoney = human.getMoney();
        //then
        assertThat(humanMoney,is(8));
    }

}
