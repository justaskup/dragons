package lt.kupliauskas.dragons.encryption;

import lt.kupliauskas.dragons.dto.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageDecryptor {

    final static String DECRYPTOR_PREFIX = "Decryptor_";
    private final Map<String, DecryptorInterface> decryptors;

    @Autowired
    public MessageDecryptor(Map<String, DecryptorInterface> decryptors) {
        this.decryptors = decryptors;
    }

    public void decryptMessage(Message message) {
        if (decryptors.containsKey(DECRYPTOR_PREFIX + message.getEncrypted())) {
            DecryptorInterface decryptor = decryptors.get(DECRYPTOR_PREFIX + message.getEncrypted());
            message.setAdId(decryptor.decrypt(message.getAdId()));
            message.setProbability(decryptor.decrypt(message.getProbability()));
            message.setText(decryptor.decrypt(message.getText()));
        }
    }
}
