package lt.kupliauskas.dragons.encryption;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("Decryptor_1")
public class Base64Decryptor implements DecryptorInterface {

    @Override
    public String decrypt(String text) {
        return new String(Base64.getDecoder().decode(text));
    }
}
