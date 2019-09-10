package top.luoren.common.util;

/**
 * @author luoren
 * @date 2019/9/10 09:55
 */

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class PasswordUtil {
    private static final String SALT = "sboot_password_salt";

    private static final String ALGORITHM_NAME = "md5";

    private static final int HASH_ITERATIONS = 5;

    public static String encrypt(String password) {
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(SALT), HASH_ITERATIONS).toHex();
    }

    public static String encrypt(String username, String password) {
        return new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(username.toLowerCase() + SALT),
                HASH_ITERATIONS).toHex();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("admin", "admin").length());
        System.out.println(encrypt("123456"));
    }

}
