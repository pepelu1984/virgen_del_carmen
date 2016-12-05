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

import java.util.*;

public class HexEncoder
{
    public static void main (String[] args) 
    {
        String encoded = HexEncoder.encode(new byte[] { 0x60, -128 } );
        System.out.println("encoded: " + encoded);
        String decoded = new String(HexEncoder.decode(encoded));
        System.out.println("decoded: " + decoded);
    }

    public static String encode (byte[] input)
    {
        return HexEncoder.encodeInternal(input, true);
    }

    public static String encodeWithoutOutputNewlines (byte[] input)
    {
        return HexEncoder.encodeInternal(input, false);
    }
    
    private static String encodeInternal (byte[] input, boolean addNewlines)
    {
        StringBuffer sb = new StringBuffer();
        int row = 0;
        for (byte b : input) {
            sb.append(HexEncoder.byteToHex(b));
            row += 2;
            if (addNewlines && row == 60) {
                sb.append("\r\n");
                row = 0;
            }
        }

        if (addNewlines) 
            sb.append("\r\n");

        return sb.toString();
    }
    
    public static byte[] decode (String input)
    {
        if (input == null) return null;
        input = input.replaceAll("[\\r\\n]", "");
        ArrayList<Byte> buf = new ArrayList<Byte>();
        for (int i = 0; i < input.length(); i += 2) {
            if (i+1 >= input.length()) return null;
            String doublet = input.substring(i, i+2);
            int n = Integer.parseInt(doublet, 16);
            n = n & 0xFF;
            buf.add(new Byte((byte)n));
        }
    
        byte[] res = new byte[buf.size()];
        for (int i = 0; i < buf.size(); i++) {
            res[i] = buf.get(i);
        }
        return res;
    }

    public static String byteToHex (byte b) 
    {
        int i = b & 0xFF;
        String hex = Integer.toHexString(i).toLowerCase();
        if (hex.length() == 1) hex = "0"+hex;
        return hex;
    }
}

