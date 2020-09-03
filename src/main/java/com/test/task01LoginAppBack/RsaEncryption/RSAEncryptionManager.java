package com.test.task01LoginAppBack.RsaEncryption;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAEncryptionManager {
    // @Autowired
    // private Environment env;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new FileSystemResource("/application-util.properties"));
        System.out.println("=============================" + properties.toString());
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }

    @Value("${privateKey}")
    private String key;

    private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANWhNYgLTdDpCY+eBrUL2ggj1VzAn9GIYjEDsjsqnbSJyBCf13NM4098h09gb3o1DOzJ5addN/U2WGmMCXcQAP7qnbKd+uSeKudUgJXtBb/dlP3vZqi7dFfauXHngC8ieiyJox3k17rBFQJRp/x2R/ap3nQbgJWr75Eq36RtQDppAgMBAAECgYATete+yAkYe6d7iRVjfOksxNe31Xi9SoppncSq0IAA7m9n1Kt4wBIaNWjBPDHuGthK3z4pvL5VhdcaCML0MGlmqoEiHVRB5Lb3XjRqLQQOoOlM2CZRN+RC4BOWfqw73/HxgL57eJ+WzKrE1kXNwmyCWsd5yIFvuRKjouczEy6hgQJBAP5saVUEvISo5fsE8Uaf2NhAc5Z7AKoj2llZWAFjAEbgcBjm4NjQawTvl2wObo71To8WZgT3DKzmVX2acRANXr0CQQDW9BZJt3mfICjht5mfRucmk1n2xY7IxOQhe+vhMwHI2GOQm4BBW/vuzJe/GpS/eNW2+eT23tvSud3hlSMnqesdAkAeBULEgmOQL7G7VdD2aWQX2DsLVYY1jTd1dYJZ34mn01HGzm9Xhfgqw+6VFHrdKXrUjWsKSPBY8nDB+lg+8aTtAkBAH/sSLMEEnpRzmdEjegR1NpotCwyoikxATPa2bQAISb2SI0GkvesvdyuB4E0lvceL2Yrbtuo/OCGgLVyadY9lAkEApvxu/GVOHwrmkGTw4JOqcUuFlKdQsvVNgsu4tWE1KJNTVk/lqIXck/5kZU7NPPGckklR0v9+xtaWdnqVZFt4AA==";
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDVoTWIC03Q6QmPnga1C9oII9VcwJ/RiGIxA7I7Kp20icgQn9dzTONPfIdPYG96NQzsyeWnXTf1NlhpjAl3EAD+6p2ynfrknirnVICV7QW/3ZT972aou3RX2rlx54AvInosiaMd5Ne6wRUCUaf8dkf2qd50G4CVq++RKt+kbUA6aQIDAQAB";


    private static Cipher getCipherInstance() throws NoSuchAlgorithmException, NoSuchPaddingException {
        // should mention RSA/ECB/PKCS1Padding to avoid platform dependent cipher
        return Cipher.getInstance("RSA/ECB/PKCS1Padding");
    }

    // https://docs.oracle.com/javase/8/docs/api/java/security/spec/PKCS8EncodedKeySpec.html
    private static PrivateKey getPrivate() throws Exception {
        // System.out.println("-------privateKey: " + privateKey);
        // System.out.println(env.getProperty("privateKey"));
        byte[] keyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private static String decryptText(String msg, PrivateKey key)
            throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException,
            NoSuchAlgorithmException, NoSuchPaddingException {
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.DECRYPT_MODE, key);
        return new String(cipher.doFinal(Base64.decodeBase64(msg)), "UTF-8");
    }

    public static String decrypt(String encrypted_msg) throws Exception {
        try {
            PrivateKey privateKey = getPrivate();
            return decryptText(encrypted_msg, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    // https://docs.oracle.com/javase/8/docs/api/java/security/spec/X509EncodedKeySpec.html
    private static PublicKey getPublic() throws Exception {
        byte[] keyBytes = Base64.decodeBase64(publicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    private static String encryptText(String msg, PublicKey key)
            throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = getCipherInstance();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(cipher.doFinal(msg.getBytes("UTF-8")));
    }

    public static String encrypt(String msg) throws Exception {
        PublicKey publicKey = getPublic();
        return encryptText(msg, publicKey);
    }

    public String getKey() {
        System.out.println(":::::::::::KEY:  " + key + " ::::::::::::");
        return this.key;
    }

    public static void main(String[] args) throws Exception {
        String text = "123456";
        String encWithKey = RSAEncryptionManager.encrypt(text);
        System.out.println("encWithKey: "+encWithKey);

        String decr2 =RSAEncryptionManager.decrypt(encWithKey);
        System.out.println("decr2: "+decr2);

        System.out.println();


    }
}
