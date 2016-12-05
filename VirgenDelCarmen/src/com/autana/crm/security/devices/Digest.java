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

import java.security.MessageDigest;

public class Digest
{
    public static byte[] md5 (byte[] input)
        throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input);
        return md.digest();
    }

    public static byte[] sha1 (byte[] input)
        throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(input);
        return md.digest();
    }
}

