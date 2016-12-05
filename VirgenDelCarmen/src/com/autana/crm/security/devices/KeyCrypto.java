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


import java.security.Key;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.Signature;
import javax.crypto.Cipher;

public class KeyCrypto
{
    private static final String sigCipherName = "SHA1withRSA";
    
    public static void main (String[] args) 
        throws Exception
    {
        KeyManager km = new KeyManager();
        km.setDebug(true);
        km.generateKeys(512);
        byte[] encrypted = KeyCrypto.encrypt(args[0].getBytes(), km.getPublic(), "RSA");
        byte[] decrypted = KeyCrypto.decrypt(encrypted, km.getPrivate(), "RSA");
        System.out.println("result: "+ new String(decrypted));
    }

    public static byte[] encrypt (byte[] input, Key key, String cipherName)
        throws Exception
    {
        Cipher cipher = Cipher.getInstance(cipherName);
        if (cipher == null) throw new Exception("cannot initialize cipher: "+cipherName);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(input);
    }

    public static byte[] decrypt (byte[] input, Key key, String cipherName)
        throws Exception
    {
        Cipher cipher = Cipher.getInstance(cipherName);
        if (cipher == null) throw new Exception("cannot initialize cipher: "+cipherName);
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(input);
    }
    
    public static byte[] sign (byte[] msg, PrivateKey privKey)
        throws Exception
    {
        Signature sig = Signature.getInstance(sigCipherName);
        if (sig == null) throw new Exception("cannot initialize signature instance: "+sigCipherName);
        sig.initSign(privKey);
        sig.update(msg);

        return sig.sign();
    }
    
    public static boolean verify (byte[] msg, byte[] signature, PublicKey pubKey)
        throws Exception
    {
        Signature sig = Signature.getInstance(sigCipherName);
        if (sig == null) throw new Exception("cannot initialize signature instance: "+sigCipherName);
        sig.initVerify(pubKey);
        sig.update(msg);

        return sig.verify(signature);
    }
}

