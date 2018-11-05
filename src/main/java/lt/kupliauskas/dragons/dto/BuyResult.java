package lt.kupliauskas.dragons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyResult {
    @JsonProperty(value = "shoppingSuccess")
    private boolean success;
    private int gold;
    private int lives;
    private int level;
    private int turn;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean status) {
        this.success = status;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }
}
