/*
 *  This file is part of MP by anti43 /GPL.
 *  
 *      MP is free software: you can redistribute it and/or modify
 *      it under the terms of the GNU General Public License as published by
 *      the Free Software Foundation, either version 3 of the License, or
 *      (at your option) any later version.
 *  
 *      MP is distributed in the hope that it will be useful,
 *      but WITHOUT ANY WARRANTY; without even the implied warranty of
 *      MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *      GNU General Public License for more details.
 *  
 *      You should have received a copy of the GNU General Public License
 *      along with MP.  If not, see <http://www.gnu.org/licenses/>.
 */
package mp4.benutzerverwaltung;

import java.security.*;

/**
 *
 * @author pertinax
 */
public class md5hash {

    private MessageDigest md = null;
    static private md5hash md5 = null;
    private static final char[] hexChars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * Constructor is private so you must use the getInstance method
     */
    private md5hash() throws NoSuchAlgorithmException {
        md = MessageDigest.getInstance("MD5");
    }

    /**
     * This returns the singleton instance
     * @return 
     * @throws NoSuchAlgorithmException
     */
    public static md5hash getInstance() throws NoSuchAlgorithmException {

        if (md5 == null) {
            md5 = new md5hash();

        }

        return (md5);
    }

    public String hashData(byte[] dataToHash) {

        return hexStringFromBytes((calculateHash(dataToHash)));
    }

    private byte[] calculateHash(byte[] dataToHash) {
        md.update(dataToHash, 0, dataToHash.length);

        return (md.digest());
    }

    public String hexStringFromBytes(byte[] b) {

        String hex = "";

        int msb;

        int lsb = 0;
        int i;

        // MSB maps to idx 0

        for (i = 0; i < b.length; i++) {

            msb = ((int) b[i] & 0x000000FF) / 16;

            lsb = ((int) b[i] & 0x000000FF) % 16;
            hex = hex + hexChars[msb] + hexChars[lsb];
        }
        return (hex);
    }
} 