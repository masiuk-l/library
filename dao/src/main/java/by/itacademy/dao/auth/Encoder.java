package by.itacademy.dao.auth;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Project KR. Created by masiuk-l on 20.08.2017.
 */
public class Encoder {

    /**
     * @param pwd password input
     * @return encoded password
     */
    public static String encode(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(pwd.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            //System.out.println(encoder.encode(digest));
            return encoder.encode(digest);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

}
