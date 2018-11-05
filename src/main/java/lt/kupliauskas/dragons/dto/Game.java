package lt.kupliauskas.dragons.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {
    private String gameId;
    private int lives;
    private int gold;
    private int level;
    private int score;
    private int turn;

    private final HashMap<String,Integer> ownedItemCounts = new HashMap<>();

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public HashMap<String, Integer> getOwnedItemCounts() {
        return ownedItemCounts;
    }

    @Override
    public String toString() {
        return String.format(
            "Game %s\tlives: %d\tgold: %d\tlevel: %d\tscore: %d\tturn: %d",
            gameId,
            lives,
            gold,
            level,
            score,
            turn
        );
    }
}
