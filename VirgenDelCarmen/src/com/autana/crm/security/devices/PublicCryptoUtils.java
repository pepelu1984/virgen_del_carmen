package com.autana.crm.security.devices;

import java.io.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class PublicCryptoUtils {
	private static final int version = 11;
    private static Boolean synchro = new Boolean(true);
    private Exception lastException;
    private KeyManager keyManager;
    private String passwordCipher;
    private int symCipherKeySize;
    private PasswordCrypto pc;
    private boolean debug;
    private boolean raiseExceptions;
    private PublicKeyCrypto pkc;
    private String signature;
    private boolean persistPrivateKey;
    private boolean statePrepared;


	
	 public void prepare ()
     throws Exception
 {
     System.out.println("prepare");
     pc = new PasswordCrypto();
     if (passwordCipher != null) pc.setCipherName(passwordCipher);

     keyManager = new KeyManager();
     if (passwordCipher != null) keyManager.setCipherName(passwordCipher);

     pkc = new PublicKeyCrypto();
     if (symCipherKeySize != 0) pkc.setSymCipherKeySize(symCipherKeySize);

     if (persistPrivateKey) {
         // reload the private key if we already have it
         String privateKeyDecryptedHex ="";// getPersistentString("privateKeyDecryptedHex");
         if (privateKeyDecryptedHex != null) {
             if (! setPrivateKey(privateKeyDecryptedHex, null)) {
                 //setPersistentString("privateKeyDecryptedHex", null); 
             }
         }
     }

     statePrepared = true;
 }

 public boolean isPrepared ()
 {
     return statePrepared;
 }

 public void prepareCore ()
     throws Exception
 {
     raiseExceptions = true;
     persistPrivateKey = false;

     pc = new PasswordCrypto();
     keyManager = new KeyManager();
     pkc = new PublicKeyCrypto();
 }

 public boolean checkUnlimitedPolicy ()
     throws Exception
 {
     try {
         // pbe
         PasswordCrypto pcLocal = new PasswordCrypto();
         pcLocal.encrypt(SecurityUtils.getBytes("hello"), new String("pass").toCharArray());

         // aes
         KeyManager kmSender = new KeyManager();
         kmSender.generateKeys(512);
         KeyManager kmRecipient = new KeyManager();
         kmRecipient.generateKeys(512);
         
         PublicKeyCrypto pkcLocal = new PublicKeyCrypto();
         pkcLocal.encrypt(SecurityUtils.getBytes("hello"), kmSender.getPrivate(), kmRecipient.getPublic());
     }
     catch (InvalidKeyException e) {
         return false;
     }
     catch (NoSuchAlgorithmException e) {
         return false;
     }
     catch (Exception e) {
         handleException(e);
         return false;
     }

     return true;
 }
 

 public Exception getLastException ()
 {
     return lastException;
 }
 
 public String getLastExceptionStackTrace ()
 {
     if (lastException == null) {
         return null;
     }
     else {
         StringWriter sw = new StringWriter();
         PrintWriter pw = new PrintWriter(sw, true);
         lastException.printStackTrace(pw);
         pw.close();
         return sw.toString();
     }
 }

 private void handleException (Exception e)
     throws Exception
 {
     lastException = e;
     if (raiseExceptions) {
         throw e;
     }
     else {
         e.printStackTrace();
     }
 }

 public int getVersion ()
 {
     return version;
 }
 
 public String getJavaVersion ()
 {
     return System.getProperty("java.version");
 }


 /* digests */

 public String md5 (String s)
     throws Exception
 {
     try {
         return HexEncoder.encodeWithoutOutputNewlines(Digest.md5(SecurityUtils.getBytes(s)));
     }
     catch (Exception e) {
         handleException(e);
         return null;
     }
 }

 public String sha1 (String s)
     throws Exception
 {
     try {
         return HexEncoder.encodeWithoutOutputNewlines(Digest.sha1(SecurityUtils.getBytes(s)));
     }
     catch (Exception e) {
         handleException(e);
         return null;
     }
 }

 
 /* password based encryption */

 public String encrypt (String clearText, String passWord)
     throws Exception
 {
     try {
         return HexEncoder.encode(pc.encrypt(SecurityUtils.getBytes(clearText), passWord.toCharArray()));
     }
     catch (Exception e) {
         handleException(e);
         return null;
     }
 }

 public String decrypt (String hexCipherText, String passWord)
     throws Exception
 {
     try {
         return SecurityUtils.newString(pc.decrypt(HexEncoder.decode(hexCipherText), passWord.toCharArray()));
     }
     catch (Exception e) {
         handleException(e);
         return null;
     }
 }

 
 /* RSA keys based encryption */

 public boolean setPublicKey (String hexKey)
     throws Exception
 {
     try {
         if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
         if (hexKey == null || hexKey.length() == 0) throw new Exception("empty key");
         keyManager.setPublicHex(hexKey);
         return true;
     }
     catch (Exception e) {
         handleException(e);
         return false;
     }
 }

 // passPhrase may be null
 public boolean setPrivateKey (String hexKey, String passPhrase)
     throws Exception
 {
     try {
         if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
         if (hexKey == null || hexKey.length() == 0) throw new Exception("empty key");
         keyManager.setPrivateHex(hexKey, passPhrase);
     }
     catch (Exception e) {
         handleException(e);
         return false;
     }
     
     if (persistPrivateKey) {
         // ok, we'll store the decrypted private key for later use in case the 
         // applet gets reloaded during this session
         // the key gets reloaded in prepare()
        // setPersistentString("privateKeyDecryptedHex", keyManager.getPrivateHex(null));
     }
         
     return true;
 }


 /*  public key cryptography */

 public void setSignature (String hexSignature)
 {
     signature = hexSignature;
 }

 public String getSignature ()
 {
     return signature;
 }

 public String encryptAndSign (String clearText, String hexRecipientPublicKey)
     throws Exception
 {
     try {
         if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
         if (pkc == null) throw new Exception("pkc is NULL - have you called prepare() ?!");
         if (hexRecipientPublicKey == null || hexRecipientPublicKey.length() == 0) throw new Exception("hexRecipientPublicKey must be specified");
         String res = pkc.encrypt(SecurityUtils.getBytes(clearText), keyManager.getPrivate(), keyManager.decodePublicKey(hexRecipientPublicKey));
         signature = pkc.getSignature();
         signature = signature;
         return res;
     }
     catch (Exception e) {
         handleException(e);
         return null;
     }
 }

 public String decryptAndCheckSignature (String hexCipherText, String hexSenderPublicKey)
     throws Exception
 {
//     try {
//         if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
//         if (pkc == null) throw new Exception("pkc is NULL - have you called prepare() ?!");
//         if (keyManager.getPrivate() == null) throw new Exception("keyManager.getPrivate() returned no key");
//         if (signature == null || signature.length() == 0) throw new Exception("signature is NULL - have you called setSignature() ?!");
//         if (hexSenderPublicKey == null || hexSenderPublicKey.length() == 0) throw new Exception("hexSenderPublicKey must be specified");
//         
//         pkc.setSignature(signature);
//         
//         
//         X509EncodedKeySpec spec = new X509EncodedKeySpec(hexSenderPublicKey.getBytes());
//         KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//         PublicKey clavePrincipal= keyFactory.generatePublic(spec);
//         
//         
//         
//         return Util.newString(pkc.decrypt(hexCipherText, keyManager.getPrivate(), clavePrincipal));
//     }
//     
//     
//     
//     catch (Exception e) {
//         handleException(e);
//         return null;
//     }
	 
	  try {
          if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
          if (pkc == null) throw new Exception("pkc is NULL - have you called prepare() ?!");
          if (keyManager.getPrivate() == null) throw new Exception("keyManager.getPrivate() returned no key");
          if (signature == null || signature.length() == 0) throw new Exception("signature is NULL - have you called setSignature() ?!");
          if (hexSenderPublicKey == null || hexSenderPublicKey.length() == 0) throw new Exception("hexSenderPublicKey must be specified");

          pkc.setSignature(HexEncoder.encode(signature.getBytes()));
          //hexCipherText=hexCipherText.replaceAll("\\\n", "");
          //hexCipherText=hexCipherText.replaceAll("\\\r", "");
          hexSenderPublicKey=hexSenderPublicKey.replaceAll("\\\n", "");
          hexSenderPublicKey=hexSenderPublicKey.replaceAll("\\\r", "");
          
          
          return SecurityUtils.newString(pkc.decrypt(hexCipherText, keyManager.getPrivate(), keyManager.decodePublicKey(hexSenderPublicKey)));
      }
      catch (Exception e) {
          handleException(e);
          return null;
      }

      
 }


 /* RSA keys based message signing */
 
 public String sign (String msg)
     throws Exception
 {
     try {
         if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
         if (keyManager.getPrivate() == null) throw new Exception("keyManager.getPrivate() returned no key");
         if (msg == null || msg.length() == 0) throw new Exception("message data to sign is empty");
         return HexEncoder.encode(KeyCrypto.sign(SecurityUtils.getBytes(sha1(msg)), keyManager.getPrivate()));
     }
     catch (Exception e) {
         handleException(e);
         return null;
     }
 }
 
 public boolean verify (String msg, String signature, String hexPublicKey)
     throws Exception
 {
     try {
         if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
         if (msg == null || msg.length() == 0) throw new Exception("message data to sign is empty");
         if (signature == null || signature.length() == 0) throw new Exception("signature is empty");
         if (hexPublicKey == null || hexPublicKey.length() == 0) throw new Exception("hexPublicKey is empty");
         return KeyCrypto.verify(SecurityUtils.getBytes(sha1(msg)), HexEncoder.decode(signature), keyManager.decodePublicKey(hexPublicKey));
     }
     catch (Exception e) {
         handleException(e);
         return false;
     }
 }

 
 /* RSA keys creation */

 public String getPublicKey ()
     throws Exception
 {
     if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
     return keyManager.getPublicHex();
 }

 // passPhrase may be null
 public String getPrivateKey (String passPhrase)
     throws Exception
 {
     try {
         if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
         return keyManager.getPrivateHex(passPhrase);
     }
     catch (Exception e) {
         handleException(e);
         return null;
     }
 }

 public boolean hasPrivateKey ()
     throws Exception
 {
     if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
     if (keyManager.getPrivate() == null)
         return false;
     else 
         return true;
 }

 public void clearKeys ()
     throws Exception
 {
     if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
     keyManager.clearKeys();
     if (persistPrivateKey) {
         //setPersistentString("privateKeyDecryptedHex", null); 
     }
     pkc.reset();
     signature = null;
 }

 public boolean generateKeys (int bitSize)
     throws Exception
 {
     try {
         if (keyManager == null) throw new Exception("keyManager is NULL - have you called prepare() ?!");
         keyManager.generateKeys(bitSize);
         return true;
     }
     catch (Exception e) {
         handleException(e);
         return false;
     }
 }

 public String getFingerPrint (String hexStr)
     throws Exception
 {
     return HexEncoder.encodeWithoutOutputNewlines(Digest.sha1(HexEncoder.decode(hexStr)));
 }

 public String hexEncode (String str)
     throws Exception
 {
     return HexEncoder.encode(SecurityUtils.getBytes(str));
 }
 
 public String hexDecode (String str)
     throws Exception
 {
     return SecurityUtils.newString(HexEncoder.decode(str));
 }


}
