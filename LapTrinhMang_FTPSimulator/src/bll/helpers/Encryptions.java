
package bll.helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author HP
 */
public class Encryptions {
    
     public static String md5(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] messageDigest = digest.digest(str.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while(hashtext.length() < 32){
                hashtext = "0" +hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
     
     public static void main(String[] args) {
         System.out.println(Encryptions.md5("vinh01121999"));
    }
    
}
