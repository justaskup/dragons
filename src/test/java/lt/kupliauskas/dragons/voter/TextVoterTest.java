package lt.kupliauskas.dragons.voter;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import lt.kupliauskas.dragons.dto.Classification;
import lt.kupliauskas.dragons.dto.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class TextVoterTest {
    private TextVoter voter;

    @Before
    public void setUp() {
        voter = new TextVoter();
        HashMap<String, Classification> classifications = new HashMap<>();

        Classification stealClassification = new Classification();
        stealClassification.setPattern("Steal .* from .*");
        stealClassification.setScore(-100);
        classifications.put("steal", stealClassification);

        Classification helpClassification = new Classification();
        helpClassification.setPattern("Help .* to [^ ]+ (the|a).*");
        helpClassification.setScore(50);
        classifications.put("help", helpClassification);

        voter.setClassifications(classifications);
    }

    @Test
    @Parameters(method = "getData")
    public void testVote(Message message, int expectedScore) {
        int score = voter.vote(message);
        assertEquals(expectedScore, score);
    }

    public Object getData() {
        Message message1 = new Message();
        message1.setText("Steal beer mug from Bev Bradford");
        Message message2 = new Message();
        message2.setText("Help Xandra Baldrick to write their biographical novel about their difficulties with a deranged clothes");
        Message message3 = new Message();
        message3.setText("Something new");

        return $(
                $(message1, -100),
                $(message2, 50),
                $(message3, 0)
        );

    }
}
