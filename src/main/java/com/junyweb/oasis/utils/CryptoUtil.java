package com.junyweb.oasis.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoUtil {
    public static class Sha512 {
        private Sha512() {
        }

        public static String hash(String input) {
            return Sha512.hash(input, null);
        }

        private static String hash(String input, String fallback) {     // 비밀번호 함호화
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
                messageDigest.reset();
                messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
                return String.format("%0128x", new BigInteger(1,messageDigest.digest()));
            } catch (NoSuchAlgorithmException ignored) {
                return  null;
            } catch (Exception ignored) {
                return fallback;
            }
        }
    }
    private CryptoUtil() {

    }
}
