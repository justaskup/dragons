package lt.kupliauskas.dragons;

import lt.kupliauskas.dragons.dto.Message;
import lt.kupliauskas.dragons.voter.Voter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConfigurationProperties(prefix = "message-selector")
public class MessageSelector {

    private final Voter voter;

    @Autowired
    public MessageSelector(Voter voter) {
        this.voter = voter;
    }

    private static final Logger logger = LoggerFactory.getLogger(MessageSelector.class);


    Message selectMessage(List<Message> messages) {
        int bestScore = 0;
        Message bestMessage = null;
        logger.info("DECIDING ON MESSAGE");

        for (Message message: messages) {
            int score = voter.vote(message);

            logger.info(message.toString() + " SCORE: " + score);


            if (score > bestScore) {
                bestMessage = message;
                bestScore = score;
            }
        }

        return bestMessage;
    }
}
