package pl.edu.wszib.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class SecurityUtils {
    private static final String SEED = "$QTK'7,DJl{SM)e]^3}JaTU[I%EI82";

    public static String hashPasswordWithSeed(String password) {
        if (password == null) return null;
        return DigestUtils.md5Hex(password + SEED);
    }
}