package com.rap.omc.api.util.omc;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    public static String makeHashKeyValue(String keyValue){
        try {
            byte[] bytesOfMessage = keyValue.getBytes("UTF-8");
            MessageDigest md= MessageDigest.getInstance("MD5");
            //MessageDigest md= MessageDigest.getInstance("SHA-256");
            byte[] thedigest = md.digest(bytesOfMessage);
            return toHex(thedigest);
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
            return keyValue;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return keyValue;
        }
    }
    private static String toHex(byte hash[]){
        StringBuffer buf = new StringBuffer(hash.length * 2);
        for (byte element: hash) {
            int intVal = element & 0xff;
            if (intVal < 0x10){
                buf.append("0");
            }
            buf.append(Integer.toHexString(intVal));
        }
        return buf.toString();
    }
}
