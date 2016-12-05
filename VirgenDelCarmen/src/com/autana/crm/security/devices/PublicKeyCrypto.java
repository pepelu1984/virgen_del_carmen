/*

    Cryptoapplet - Java Applet providing cryptography to Javascript    

    Copyright (C) 2009 Tomas Styblo <tripie@cpan.org>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.autana.crm.security.devices;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class PublicKeyCrypto 
{
    private static final String sigCipherName = "SHA1withRSA";
    //private static final String sigCipherName = "SHA1withRSA";

    private int symCipherKeySize;
    private String signature;

    public void generateKeys(){
    	
    	 KeyManager kmSender = new KeyManager();
         try {
 			kmSender.generateKeys(512);
 			System.out.println("kmSender.getPublicHex()");
 			System.out.println(kmSender.getPublicHex());
 			System.out.println("kmSender.getPrivateHex()");
 			System.out.println(kmSender.getPrivateHex(""));
 			
 			
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         
         KeyManager kmRecipient = new KeyManager();
         try {
 			kmRecipient.generateKeys(512);
 			System.out.println("kmRecipient.getPublicHex()");
 			System.out.println(kmRecipient.getPublicHex());
 			System.out.println("kmRecipient.getPrivateHex()");
 			System.out.println(kmRecipient.getPrivateHex(""));
 		} catch (Exception e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

    	
    	
    }
    public static void main(String[] args)
        throws Exception
    {
        String secret = "Hello world";

        KeyManager kmSender = new KeyManager();
        kmSender.generateKeys(512);
        
        KeyManager kmRecipient = new KeyManager();
        kmRecipient.generateKeys(512);

        
        PublicKeyCrypto pkc = new PublicKeyCrypto();
        String encrypted = pkc.encrypt(secret.getBytes(), kmSender.getPrivate(), kmRecipient.getPublic());
        System.out.println("encrypted: " + encrypted);

        String signature = pkc.getSignature();
        System.out.println("signature: " + signature);

        pkc.reset();

        pkc.setSignature(signature);
        byte[] decrypted = pkc.decrypt(encrypted, kmRecipient.getPrivate(), kmSender.getPublic());

        System.out.println("secret: " + new String(decrypted));
    }

    public PublicKeyCrypto ()
    {
        symCipherKeySize = 128;
    }

    public void setSymCipherKeySize (int size)
    {
        symCipherKeySize = size;
    }

    public void reset ()
    {
        signature = null;
    }

    public String getSignature ()
    {
        return signature;
    }
    
    public void setSignature (String hexSig)
    {
        signature = hexSig;
    }
    

    public String encrypt (byte[] input, PrivateKey senderPrivateKey, PublicKey recipientPublicKey)
        throws Exception
    {
        // generate a new AES secret key for the sender
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(symCipherKeySize);
        SecretKey senderSecretKey = keyGen.generateKey();

        // use the new AES secret key to encrypt input
        byte[] encryptedRawInput = KeyCrypto.encrypt(input, senderSecretKey, "AES");

        // encrypt the AES secret key using the recipient's public key
        byte[] encryptedSecretKey = KeyCrypto.encrypt(senderSecretKey.getEncoded(),recipientPublicKey,"RSA");
        String hexEncryptedSecretKey = HexEncoder.encode(encryptedSecretKey);

        // generate the digest of the raw unencrypted input
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(input);
        byte byteDigestRawInput[] = md.digest();
        String strDigestRawInput = new String();
        for (int i = 0; i < byteDigestRawInput.length; i++) {
            strDigestRawInput = strDigestRawInput + Integer.toHexString((int)byteDigestRawInput[i] & 0xFF) ;
        }

        // combine the hex encoded secret key with the digest of the raw input
        String signedData = hexEncryptedSecretKey + "|" + strDigestRawInput;

        // and sign it using the sender's private key
        Signature sig = Signature.getInstance(sigCipherName);
        sig.initSign(senderPrivateKey);
        sig.update(SecurityUtils.getBytes(signedData));

        // save the hex encoded signature so that the caller has access to it using getSignature()
        signature = HexEncoder.encode(sig.sign());
        
        // combine the signed encrypted secret key+input digest with the encrypted input, inserting separators,
        // which cannot appear in the data because it has been hex encoded
        String res = HexEncoder.encode(SecurityUtils.getBytes(HexEncoder.encode(encryptedRawInput) + "|" + signedData));

        return res;
    }

        
    public byte[] decrypt (String hexInput, PrivateKey recipientPrivateKey, PublicKey senderPublicKey)
        throws Exception
    {
        // split input into the encrypted raw data and the signed encrypted secret key+digest
    	
        String s = SecurityUtils.newString(HexEncoder.decode(hexInput)); 
        byte[] encryptedRawInput = HexEncoder.decode(s.substring(0, s.indexOf("|")));
        String signedData = s.substring(s.indexOf("|") + 1);

        // verify the signature of the signed data using the sender's public key
        Signature sigVerifier = Signature.getInstance(sigCipherName);
        sigVerifier.initVerify(senderPublicKey);
        sigVerifier.update(SecurityUtils.getBytes(signedData));
        if (signature == null || signature.length() == 0) {
            throw new Exception("You must call setSignature() before calling decrypt()");
        }
       
        if (! sigVerifier.verify(HexEncoder.decode(signature))) {
            throw new Exception("invalid signature");
        }

        // split the signed data into the encrypted AES secret key and the digest of the raw unencrypted input
        byte[] encryptedSecretKey = HexEncoder.decode(signedData.substring(0, signedData.indexOf("|")));
        String signedDigestOrigInput = signedData.substring(signedData.indexOf("|") + 1);

        // decrypt the AES secret key using the recipient's private key
        byte[] decryptedSecretKey = KeyCrypto.decrypt(encryptedSecretKey, recipientPrivateKey, "RSA");

        // use the AES key to decrypt the encrypted input
        SecretKeySpec secretKeySpec = new SecretKeySpec(decryptedSecretKey, "AES");
        byte[] decryptedInput = KeyCrypto.decrypt(encryptedRawInput, secretKeySpec, "AES");

        // generate the digest of the decrypted input
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(decryptedInput);
        byte byteDigestDecryptedData[] = md.digest();
        String strDigestDecryptedData = new String();
        for (int i = 0; i < byteDigestDecryptedData.length; i++) {
            strDigestDecryptedData = strDigestDecryptedData + Integer.toHexString((int)byteDigestDecryptedData[i] & 0xFF) ;
        }

        // and compare it with the received signed digest of the original data
        if (! strDigestDecryptedData.equals(signedDigestOrigInput)) {
            throw new Exception("invalid message digest");
        }

        return decryptedInput;
    }

}

