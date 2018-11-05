package lt.kupliauskas.dragons;

import lt.kupliauskas.dragons.dto.Message;
import lt.kupliauskas.dragons.voter.Voter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class MessageSelectorTest {

    private MessageSelector messageSelector;

    @Mock
    private Voter voter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        messageSelector = new MessageSelector(voter);
    }

    @Test
    public void testSelectMessageWithHighestScore() {

        Message message1 = new Message();
        Message message2 = new Message();
        Message message3 = new Message();
        List<Message> messages =  Arrays.asList(
                message1,
                message2,
                message3
        );
        when(voter.vote(message1)).thenReturn(500);
        when(voter.vote(message2)).thenReturn(1000);
        when(voter.vote(message3)).thenReturn(700);
        Message selectedMessage = messageSelector.selectMessage(messages);
        assertEquals(message2, selectedMessage);
    }
}
