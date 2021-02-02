package cinema.shop.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class HashUtil {
    public static final String PBK_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static byte[] getSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return salt;
    }

    public static String hashPassword(String password, byte[] salt) {
        StringBuilder builder = new StringBuilder();
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 42587, 128);
        try {
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(PBK_ALGORITHM);
            byte[] hashedPassword = keyFactory.generateSecret(keySpec).getEncoded();
            for (byte entry : hashedPassword) {
                builder.append(entry);
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not find hashing algorithm. ", e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("Could not initialize key specifications. ", e);
        }
    }
}
