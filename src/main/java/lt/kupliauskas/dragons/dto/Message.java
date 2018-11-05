package lt.kupliauskas.dragons.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Message {
    private String adId;
    @JsonProperty(value = "message")
    private String text;
    private int reward;
    private int expiresIn;
    private String probability;
    private String encrypted = "0";

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Message{" +
                "adId='" + adId + '\'' +
                ", text='" + text + '\'' +
                ", reward=" + reward +
                ", expiresIn=" + expiresIn +
                ", encrypted=" + encrypted +
                ", probability='" + probability + '\'' +
                '}';
    }
}
