package lt.kupliauskas.dragons.encryption;

import lt.kupliauskas.dragons.dto.Message;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static lt.kupliauskas.dragons.encryption.MessageDecryptor.DECRYPTOR_PREFIX;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageDecryptorTest {

    private MessageDecryptor messageDecryptor;

    @Mock
    private DecryptorInterface base64Decryptor;
    @Mock
    private DecryptorInterface rot13Decryptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Map<String, DecryptorInterface> decryptors = new HashMap<>();
        decryptors.put(DECRYPTOR_PREFIX + "1", base64Decryptor);
        decryptors.put(DECRYPTOR_PREFIX + "2", rot13Decryptor);
        messageDecryptor = new MessageDecryptor(decryptors);
    }

    @Test
    public void testDecryptUnencryptedMessage() {
        Message message = new Message();
        messageDecryptor.decryptMessage(message);

        verify(base64Decryptor, never()).decrypt(anyString());
        verify(rot13Decryptor, never()).decrypt(anyString());
    }

    @Test
    public void testDecryptEncryptedMessage() {
        Message message = new Message();
        message.setEncrypted("2");
        message.setAdId("adId");
        message.setProbability("Hmmm...");
        message.setText("Example text");

        messageDecryptor.decryptMessage(message);

        verify(rot13Decryptor, times(3)).decrypt(anyString());
    }
}
