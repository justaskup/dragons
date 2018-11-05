package lt.kupliauskas.dragons.voter;

import lt.kupliauskas.dragons.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class Voter {

    private final Map<String, VoterInterface> voters;

    @Autowired
    public Voter(Map<String, VoterInterface> voters) {
        this.voters = voters;
    }

    public int vote(Message message) {
        int totalScore = 0;
        for (Map.Entry entry: voters.entrySet()) {
            VoterInterface voter = (VoterInterface) entry.getValue();
            totalScore = totalScore + voter.vote(message);
        }

        return totalScore;
    }
}
