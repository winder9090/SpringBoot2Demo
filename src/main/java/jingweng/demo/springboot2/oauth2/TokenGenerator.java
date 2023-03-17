package jingweng.demo.springboot2.oauth2;

/**
 * @projectName: SpringBoot2Demo
 * @package: jingweng.demo.springboot2.oauth2
 * @className: TokenGenerator
 * @author:
 * @description: 生成token
 * @date: 2023/3/16 16:37
 * @version: 1.0
 */

import java.security.MessageDigest;
import java.util.UUID;

public class TokenGenerator {

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static final char[] HEX_CODE = "0123456789abcdef".toCharArray();

    public static String toHexString(byte[] data) {
        if(data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length*2);
        for ( byte b : data) {
            r.append(HEX_CODE[(b >> 4) & 0xF]);
            r.append(HEX_CODE[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            return "";
        }
    }
}
