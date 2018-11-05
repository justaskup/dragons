package lt.kupliauskas.dragons.voter;

import lt.kupliauskas.dragons.MessageSelector;
import lt.kupliauskas.dragons.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "voters.probability")
public class ProbabilityVoter implements VoterInterface {

    private static final Logger logger = LoggerFactory.getLogger(MessageSelector.class);
    private HashMap<String, Integer> probabilityScores;

    public void setProbabilityScores(HashMap<String, Integer> probabilityScores) {
        this.probabilityScores = probabilityScores;
    }

    @Override
    public int vote(Message message) {
        if (probabilityScores.containsKey(message.getProbability())) {
            return probabilityScores.get(message.getProbability());
        }

        logger.warn("Unknown probability: " + message.toString());

        return 0;
    }
}
