package lt.kupliauskas.dragons.encryption;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class Rot13DecryptorTest {

    private Rot13Decryptor decryptor;

    @Before
    public void setUp() {
        decryptor = new Rot13Decryptor();
    }


    @Test
    @Parameters(method = "getData")
    public void testDecrypt(String encrypted, String decrypted) {
        assertEquals(decrypted, decryptor.decrypt(encrypted));
    }

    public Object getData() {
        return $(
                $("abcdefghijklmnopqrstuvwxyz", "nopqrstuvwxyzabcdefghijklm"),
                $("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "NOPQRSTUVWXYZABCDEFGHIJKLM"),
                $("ABC123+-", "NOP123+-")
        );
    }
}