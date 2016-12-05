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

import java.io.*;
import java.util.*;

import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyPairGenerator;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.KeyFactory;

public class KeyManager
{
    private PrivateKey priv;
    private PublicKey pub;
    private boolean debug;
    private String passwordCipher;
    private PasswordCrypto pc;

    public KeyManager ()
    {
        pc = new PasswordCrypto();
    }
    
    public void setCipherName (String name)
    {
        pc.setCipherName(name);
    }

    public void setDebug (boolean debug)
    {
        this.debug = debug;
    }

    public static void main(String[] args) 
        throws Exception 
    {
        KeyManager km = new KeyManager();
        km.setDebug(true);
        km.generateKeys(512);
        
        OutputStream file;
        file = new FileOutputStream("freedomcs-public.key");
        System.out.println("public raw digest: "+Arrays.toString(Digest.md5(km.getPublicRaw())));
        System.out.println("public raw: "+Arrays.toString(km.getPublicRaw()));
        file.write(km.getPublicHex().getBytes());
        file.close();
        
        file = new FileOutputStream("freedomcs-private.key");
        System.out.println("private encoded digest: "+Arrays.toString(Digest.md5(km.getPrivate().getEncoded())));
        System.out.println("private encoded: "+Arrays.toString(km.getPrivate().getEncoded()));
        System.out.println("private crypt raw digest: "+Arrays.toString(Digest.md5(km.getPrivateRaw("tomas"))));
        System.out.println("private crypt raw: "+Arrays.toString(km.getPrivateRaw("tomas")));
        file.write(km.getPrivateHex("tomas").getBytes());
        file.close();
        
        km.clearKeys();

        km.loadPublicHex(new FileReader("freedomcs-public.key")); 
        System.out.println("pub key format: " + km.getPublic().getFormat());
        System.out.println("new pub encoded digest: "+Arrays.toString(Digest.md5(km.getPublic().getEncoded())));
        System.out.println("new pub encoded: "+Arrays.toString(km.getPublic().getEncoded()));
        
        km.loadPrivateHex(new FileReader("freedomcs-private.key"), "tomas"); 
        System.out.println("priv key format: " + km.getPrivate().getFormat());
        System.out.println("new priv encoded digest: "+Arrays.toString(Digest.md5(km.getPrivate().getEncoded())));
        System.out.println("new priv encoded: "+Arrays.toString(km.getPrivate().getEncoded()));
    }

    public void generateKeys (int keySize) 
        throws Exception
    {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keySize);
        KeyPair pair = keyGen.generateKeyPair();

        pub = pair.getPublic();
        debug("pub key format: " + pub.getFormat());

        priv = pair.getPrivate();
        debug("priv key format: " + priv.getFormat());
    }

    public void clearKeys ()
    {
        pub = null;
        priv = null;
    }
    
    /* public key */

    public PublicKey getPublic () 
    {
        return pub;
    }

    public String getPublicHex () 
    {
        return HexEncoder.encode(pub.getEncoded());
    }

    public byte[] getPublicRaw ()
    {
        return pub.getEncoded();
    }
    
    public void setPublicRaw (byte[] key)
        throws Exception
    {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        pub = keyFactory.generatePublic(spec);
    }

    public void setPublicHex (String key)
        throws Exception
    {
        byte[] raw = HexEncoder.decode(key);
        setPublicRaw(raw);
    }

    public void loadPublicRaw (InputStream in)
        throws Exception
    {
        setPublicRaw(loadRaw(in));
    }
    
    public void loadPublicHex (Reader in)
        throws Exception
    {
        setPublicHex(loadHex(in));
    }

    private String loadHex (Reader in)
        throws Exception
    {
        StringBuffer strbuf = new StringBuffer();
        char[] buf = new char[1024];
        int num;
        while ((num = in.read(buf, 0, buf.length)) > 0) { 
            strbuf.append(buf, 0, num);
        }
        return strbuf.toString();
    }
    
    private byte[] loadRaw (InputStream in)
        throws Exception
    {
        byte[] buf = new byte[65000];
        int num = 0;
        int total = 0;
        while ((num = in.read(buf, total, buf.length - total)) > 0) {
            total += num;
        }
        return Arrays.copyOf(buf, total);
    }
   
    public PublicKey decodePublicKey (String hexKey)
        throws Exception
    {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(HexEncoder.decode(hexKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    } 
    
    public PrivateKey decodePrivateKey (String hexKey)
        throws Exception
    {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(HexEncoder.decode(hexKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    } 

    /* private key */

    public PrivateKey getPrivate () 
    {
        return priv;
    }

    public String getPrivateHex (String passPhrase) 
        throws Exception
    {
        if (passPhrase != null && passPhrase.length() > 0) {
            return HexEncoder.encode(pc.wrap(priv, passPhrase.toCharArray()));
        }
        else {
            return HexEncoder.encode(priv.getEncoded());
        }
    }

    public byte[] getPrivateRaw (String passPhrase)
        throws Exception
    {
        if (passPhrase == null) {
            return priv.getEncoded();
        }
        else {
            return pc.wrap(priv, passPhrase.toCharArray());
        }
    }
    
    public void setPrivateRaw (byte[] key, String passPhrase)
        throws Exception
    {
        if (passPhrase == null) {
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            priv = keyFactory.generatePrivate(spec);
        }
        else {
            priv = pc.unwrap(key, passPhrase.toCharArray());
        }
    }

    public void setPrivateHex (String key, String passPhrase)
        throws Exception
    {
        setPrivateRaw(HexEncoder.decode(key), passPhrase);
    }

    public void loadPrivateRaw (InputStream in, String passPhrase)
        throws Exception
    {
        setPrivateRaw(loadRaw(in), passPhrase);
    }
    
    public void loadPrivateHex (Reader in, String passPhrase)
        throws Exception
    {
        setPrivateHex(loadHex(in), passPhrase);
    }

    private void debug (String msg)
    {
        if (debug) System.out.println(msg);
    }

}

