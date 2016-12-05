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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils
{

	public static String getMd5Data(String toEnc) {
		MessageDigest mdEnc = null;
		try {
			mdEnc = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Encryption algorithm
		mdEnc.update(toEnc.getBytes(), 0, toEnc.length());
		String md5 = new BigInteger(1, mdEnc.digest()).toString(16); // Encrypted
																		// string
		return md5;
	}
	
    public static byte[] getBytes (String str)
    {
        try {
            return str.getBytes("UTF-8");
        }
        catch (Exception e) {
            return str.getBytes();
        }
    }
    
    public static String newString (byte[] data)
    {
        try {
            return new String(data, "UTF-8");
        }
        catch (Exception e) {
            return new String(data);
        }
    }
    
    public static void main(String[] args){
    	
    	System.out.println(SecurityUtils.getMd5Data("elputoamo"));
    	
    
    }
}

