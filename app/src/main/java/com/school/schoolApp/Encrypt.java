package com.school.schoolApp;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class Encrypt {

    private static final String KEY="1Hbfh667adfDEJ78";
    private static final String ALGORITHM="AES";

    public String hash(String value) throws Exception{
        Key key=generateKey ();
        Cipher cipher=Cipher.getInstance(Encrypt.ALGORITHM);
        cipher.init (ENCRYPT_MODE,key);
        byte[] encryptedBytevalue=cipher.doFinal(value.getBytes("utf-8"));
        String encrptedValue64= Base64.encodeToString (encryptedBytevalue,Base64.DEFAULT);
        return encrptedValue64;

    }

    public static Key generateKey(){

        Key key=new SecretKeySpec (Encrypt.KEY.getBytes (),Encrypt.ALGORITHM);
        return key;
    }

}
