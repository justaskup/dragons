package lt.kupliauskas.dragons.voter;

import lt.kupliauskas.dragons.dto.Message;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "voters.reward")
public class RewardVoter implements VoterInterface {
    private int multiplier;

    public void setMultiplier(int multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public int vote(Message message) {

        return message.getReward() * multiplier;
    }
}
