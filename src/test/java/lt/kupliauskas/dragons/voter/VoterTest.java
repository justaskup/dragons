package lt.kupliauskas.dragons.voter;

import lt.kupliauskas.dragons.dto.Message;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class VoterTest {
    private Voter voter;

    @Mock
    VoterInterface firstVoter;
    @Mock
    VoterInterface secondVoter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Map<String, VoterInterface> voters = new HashMap<>();
        voters.put("FirstVoter", firstVoter);
        voters.put("SecondVoter", secondVoter);

        voter = new Voter(voters);
    }

    @Test
    public void testVoteSumsUpAllVotes() {
        when(firstVoter.vote(any(Message.class))).thenReturn(10);
        when(secondVoter.vote(any(Message.class))).thenReturn(20);

        int score = voter.vote(new Message());
        assertEquals(30, score);
    }
}
