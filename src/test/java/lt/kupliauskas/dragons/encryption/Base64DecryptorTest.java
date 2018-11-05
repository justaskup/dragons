package lt.kupliauskas.dragons.encryption;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junitparams.JUnitParamsRunner.$;
import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class Base64DecryptorTest {

    private Base64Decryptor decryptor;

    @Before
    public void setUp() {
        decryptor = new Base64Decryptor();
    }

    @Test
    @Parameters(method = "getData")
    public void testDecrypt(String encrypted, String decrypted) {
        assertEquals(decrypted, decryptor.decrypt(encrypted));
    }

    public Object getData() {
        return $(
                $("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo=", "abcdefghijklmnopqrstuvwxyz"),
                $("QUJDREVGR0hJSktMTU5PUFFSU1RVVldYWVo=", "ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
                $("QUJDMTIzKy0=", "ABC123+-")
        );
    }
}
