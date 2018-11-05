package lt.kupliauskas.dragons.voter;

import lt.kupliauskas.dragons.MessageSelector;
import lt.kupliauskas.dragons.dto.Classification;
import lt.kupliauskas.dragons.dto.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "voters.text")
public class TextVoter implements VoterInterface {

    private HashMap<String, Classification> classifications;
    private static final Logger logger = LoggerFactory.getLogger(MessageSelector.class);

    public void setClassifications(HashMap<String, Classification> classifications) {
        this.classifications = classifications;
    }

    @Override
    public int vote(Message message) {
        for (Map.Entry entry: classifications.entrySet()) {
            Classification classification = (Classification) entry.getValue();
            if (message.getText().matches(classification.getPattern())) {
                return classification.getScore();
            }
        }

        logger.warn("UNKNOWN message classification: " + message.getText());

        return 0;
    }
}
