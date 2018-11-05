package lt.kupliauskas.dragons;

import lt.kupliauskas.dragons.dto.Game;
import lt.kupliauskas.dragons.dto.ShopItem;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ShoppingAssistantTest {


    private ShoppingAssistant shoppingAssistant;

    @Before
    public void setUp() {
        shoppingAssistant = new ShoppingAssistant();
    }

    @Test
    public void testSelectItemToBuyHpot() {
        Game game = new Game();
        game.setLives(1);
        game.setGold(500);

        ShopItem itemToBuy = shoppingAssistant.selectItemToBuy(game, getItemsOnSale());

        assertEquals("hpot", itemToBuy.getId());
    }

    @Test
    public void testSelectItemToBuySaveForHpot() {
        Game game = new Game();
        game.setLives(1);
        game.setGold(49);

        ShopItem itemToBuy = shoppingAssistant.selectItemToBuy(game, getItemsOnSale());

        assertNull(itemToBuy);
    }

    @Test
    public void testSelectItemToBuyNothingToAfford() {
        Game game = new Game();
        game.setLives(2);
        game.setGold(99);

        ShopItem itemToBuy = shoppingAssistant.selectItemToBuy(game, getItemsOnSale());

        assertNull(itemToBuy);
    }

    @Test
    public void testSelectItemToBuySomethingThatHaveTheLeast() {
        Game game = new Game();
        game.setLives(2);
        game.setGold(500);

        game.getOwnedItemCounts().put("wings", 3);
        game.getOwnedItemCounts().put("shield", 2);
        game.getOwnedItemCounts().put("coat", 3);


        ShopItem itemToBuy = shoppingAssistant.selectItemToBuy(game, getItemsOnSale());

        assertEquals("shield", itemToBuy.getId());
    }

    private HashMap<String, ShopItem> getItemsOnSale() {
        HashMap<String, ShopItem> itemsOnSale = new HashMap<>();

        ShopItem hpot = new ShopItem();
        hpot.setCost(50);
        hpot.setId("hpot");
        itemsOnSale.put("hpot", hpot);

        ShopItem wings = new ShopItem();
        wings.setCost(100);
        wings.setId("wings");
        itemsOnSale.put("wings", wings);

        ShopItem shield = new ShopItem();
        shield.setCost(200);
        shield.setId("shield");
        itemsOnSale.put("shield", shield);


        ShopItem coat = new ShopItem();
        coat.setCost(200);
        coat.setId("coat");
        itemsOnSale.put("coat", coat);

        return itemsOnSale;
    }
}
