package lt.kupliauskas.dragons.voter;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lt.kupliauskas.dragons.dto.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ProbabilityVoterTest {
    private ProbabilityVoter voter;

    @Before
    public void setUp() {
        voter = new ProbabilityVoter();
        HashMap<String, Integer> probabilityScore = new HashMap<>();

        probabilityScore.put("Easy", 100);
        probabilityScore.put("Hard", 50);
        probabilityScore.put("Impossible mission", 10);

        voter.setProbabilityScores(probabilityScore);
    }

    @Test
    @Parameters(method = "getData")
    public void testVote(Message message, int expectedScore) {
        int score = voter.vote(message);
        assertEquals(expectedScore, score);
    }

    public Object getData() {
        Message message1 = new Message();
        message1.setProbability("Easy");
        Message message2 = new Message();
        message2.setProbability("Impossible mission");
        Message message3 = new Message();
        message3.setProbability("Something new");

        return $(
                $(message1, 100),
                $(message2, 10),
                $(message3, 0)
        );

    }
}
