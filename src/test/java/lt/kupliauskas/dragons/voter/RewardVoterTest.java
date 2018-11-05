package lt.kupliauskas.dragons.voter;

import lt.kupliauskas.dragons.dto.Message;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RewardVoterTest {

    private RewardVoter voter;

    @Before
    public void setUp() {
        voter = new RewardVoter();
        voter.setMultiplier(5);
    }

    @Test
    public void testVote() {
        Message message = new Message();
        message.setReward(40);
        int score = voter.vote(message);
        assertEquals(200, score);
    }
}
