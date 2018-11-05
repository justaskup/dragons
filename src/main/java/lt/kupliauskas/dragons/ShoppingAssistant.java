package lt.kupliauskas.dragons;

import lt.kupliauskas.dragons.dto.Game;
import lt.kupliauskas.dragons.dto.ShopItem;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingAssistant {

    ShopItem selectItemToBuy(Game game, HashMap<String, ShopItem> itemsOnSale) {

        String hpotId = "hpot";
        if (game.getLives() == 1 && itemsOnSale.containsKey(hpotId)) {
            ShopItem hpot = itemsOnSale.get(hpotId);
            if (game.getGold() >= hpot.getCost()) {
                return hpot;
            }
            return null;
        }

        itemsOnSale.remove(hpotId);

        return selectBestAffordableItem(game, itemsOnSale);
    }

    private ShopItem selectBestAffordableItem(Game game, HashMap<String, ShopItem> availableItems) {
        ShopItem bestAffordableItem = null;
        int bestAffordableItemCount = Integer.MAX_VALUE;

        for (ShopItem item: availableItems.values()) {
            if (item.getCost() > game.getGold()) {
                continue;
            }

            int itemCount = getCurrentItemCount(game, item);

            if (itemCount <= bestAffordableItemCount) {
                bestAffordableItemCount = itemCount;
                bestAffordableItem = item;
            }
        }

        return bestAffordableItem;
    }

    private int getCurrentItemCount(Game game, ShopItem item) {
        return game.getOwnedItemCounts().getOrDefault(item.getId(), 0);
    }
}
