package lt.kupliauskas.dragons;

import lt.kupliauskas.dragons.exception.ClientException;
import lt.kupliauskas.dragons.dto.Game;
import lt.kupliauskas.dragons.dto.Message;
import lt.kupliauskas.dragons.dto.MissionResult;
import lt.kupliauskas.dragons.dto.ShopItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class Bot {

    private final GameClient client;
    private final MessageSelector messageSelector;
    private final ShoppingAssistant shoppingAssistant;

    private static final Logger logger = LoggerFactory.getLogger(Bot.class);

    @Autowired
    public Bot(
            GameClient client,
            MessageSelector messageSelector,
            ShoppingAssistant shoppingAssistant
    ) {
        this.client = client;
        this.messageSelector = messageSelector;
        this.shoppingAssistant = shoppingAssistant;
    }

    Game playGame() {
        Game game = client.startNewGame();
        logger.info(String.format("Create game: %s", game.getGameId()));

        while (game.getLives() > 0) {
            logger.info(String.format("Status before move: %s", game.toString()));
            nextMove(game);
            if (game.getLives() > 0) {
                buyItems(game);
            }
        }

        return game;
    }

    private void nextMove(Game game) {
        // get messages
        try {
            List<Message> messages = client.getMessages(game);

            Message messageToPlay = messageSelector.selectMessage(messages);
            logger.info(String.format("Decided to play message: %s", messageToPlay.toString()));

            MissionResult result = client.solveMessage(game, messageToPlay);

        } catch (ClientException exception) {
            throw new RuntimeException("Got an exception during nextMove", exception);
        }

    }

    private void buyItems(Game game) {
        try {
            HashMap<String, ShopItem> itemsOnSale = client.getShopItems(game);
            ShopItem itemToBuy = shoppingAssistant.selectItemToBuy(game, itemsOnSale);

            while (itemToBuy != null) {
                logger.info(String.format("Buying %s for %d", itemToBuy.getId(), itemToBuy.getCost()));
                client.buyItem(game, itemToBuy);

                itemsOnSale = client.getShopItems(game);
                itemToBuy = shoppingAssistant.selectItemToBuy(game, itemsOnSale);
            }
        } catch (ClientException exception) {
            throw new RuntimeException("Got an exception during buyItems", exception);
        }
    }

}
