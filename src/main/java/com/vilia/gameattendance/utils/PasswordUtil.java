package com.vilia.gameattendance.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {

    private static final int SALT_LENGTH = 16; // length of salt in bytes
    private static final int HASH_ITERATIONS = 10000; // number of hash iterations

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPassword = md.digest(password.getBytes());

        for (int i = 0; i < HASH_ITERATIONS; i++) {
            md.reset();
            hashedPassword = md.digest(hashedPassword);
        }

        // store the salt and hashed password in the database
        return new String(Base64.getEncoder().encode(salt)) + "$" + new String(Base64.getEncoder().encode(hashedPassword));
    }

    public static boolean verifyPassword(String password, String hashedPassword) throws NoSuchAlgorithmException {
        String[] parts = hashedPassword.split("\\$");
        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] storedHash = Base64.getDecoder().decode(parts[1]);

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(salt);
        byte[] hashedPasswordToCheck = md.digest(password.getBytes());

        for (int i = 0; i < HASH_ITERATIONS; i++) {
            md.reset();
            hashedPasswordToCheck = md.digest(hashedPasswordToCheck);
        }

        return MessageDigest.isEqual(storedHash, hashedPasswordToCheck);
    }
}