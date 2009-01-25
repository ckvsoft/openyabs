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
package mpv5.utils.text;

/**
 *
 * @author Andreas
 */
public class TypeConversion {

    /**
     * Converts a boolean value into either "0" (false) or "1" (true)
     * @param bool
     * @return The string value
     */
    public static String booleanToString(boolean bool) {
        String booleans = "0";
        if (bool) {
            booleans = "1";
        }
        return booleans;
    }

    /**
     * Converts a String into a boolean value. Everything except the String "1" will return false.
     * @param string
     * @return
     */
    public static boolean stringToBoolean(String string) {
        boolean val = false;
        if (string.equals("1")) {
            val = true;
        }
        return val;
    }
}
