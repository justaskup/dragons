package lt.kupliauskas.dragons.encryption;

import org.springframework.stereotype.Component;

@Component("Decryptor_2")
public class Rot13Decryptor implements DecryptorInterface {

    @Override
    public String decrypt(String string) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c >= 'a' && c <= 'm' || c >= 'A' && c <= 'M') {
                c += 13;
            } else if (c >= 'n' && c <= 'z' || c >= 'N' && c <= 'Z') {
                c -= 13;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
