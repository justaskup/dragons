package lt.kupliauskas.dragons;

import lt.kupliauskas.dragons.encryption.MessageDecryptor;
import lt.kupliauskas.dragons.exception.ClientException;
import lt.kupliauskas.dragons.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Service
@ConfigurationProperties(prefix = "client")
public class GameClient {

    private String baseUrl;

    private final MessageDecryptor messageDecryptor;
    private final RestTemplate restTemplate;

    @Autowired
    public GameClient(MessageDecryptor messageDecryptor) {
        this.messageDecryptor = messageDecryptor;
        restTemplate = new RestTemplate();
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    Game startNewGame() {
        return restTemplate.postForObject(baseUrl + "/game/start", null, Game.class);
    }

    List<Message> getMessages(Game game) throws ClientException {
        Message[] messages = restTemplate.getForObject(getGameBaseUrl(game) + "/messages", Message[].class);
        if (messages == null) {
            throw new ClientException("Could not retrieve any messages");
        }

        for (Message message: messages) {
            messageDecryptor.decryptMessage(message);
        }

        return Arrays.asList(messages);
    }

    MissionResult solveMessage(Game game, Message message) throws ClientException {
        MissionResult result = restTemplate.postForObject(
                getGameBaseUrl(game) + "/solve/" + message.getAdId(),
                null,
                MissionResult.class);
        if (result == null) {
            throw new ClientException("Could not retrieve any messages");
        }

        game.setLives(result.getLives());
        game.setGold(result.getGold());
        game.setScore(result.getScore());
        game.setTurn(result.getTurn());

        return result;
    }

    HashMap<String, ShopItem> getShopItems(Game game) throws ClientException {
        ShopItem[] itemsArray = restTemplate.getForObject(getGameBaseUrl(game) + "/shop", ShopItem[].class);
        if (itemsArray == null) {
            throw new ClientException("Could not retrieve any items");
        }
        HashMap<String, ShopItem> items = new HashMap<>();
        for (ShopItem item: itemsArray) {
            items.put(item.getId(), item);
        }
        return items;
    }

    void buyItem(Game game, ShopItem item) throws ClientException {
        BuyResult result = restTemplate.postForObject(
                getGameBaseUrl(game) + "/shop/buy/" + item.getId(),
                null,
                BuyResult.class);
        if (result == null) {
            throw new ClientException("Could not get response from shop/buy");
        }

        if (result.isSuccess()) {
            game.setGold(result.getGold());
            game.setLives(result.getLives());
            game.setLevel(result.getLevel());
            game.setTurn(result.getTurn());
            game.getOwnedItemCounts().put(
                    item.getId(),
                    game.getOwnedItemCounts().getOrDefault(item.getId(), 0) + 1
            );
        }
    }

    private String getGameBaseUrl(Game game) {
        return String.format("%s/%s",baseUrl, game.getGameId());
    }
}
