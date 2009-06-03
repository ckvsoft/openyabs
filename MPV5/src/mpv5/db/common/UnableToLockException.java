/*
 *  This file is part of MP.
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
package mpv5.db.common;

import mpv5.db.objects.User;
import mpv5.logging.Log;

/**
 *
 */
public class UnableToLockException extends Exception {
    private static final long serialVersionUID = 1L;

    public UnableToLockException(Context context, int id, User user) {
        super("Unable to lock item: " + context.getDbIdentity() + " with ID " + id + " for user: " + user);
        Log.Debug(this);
    }
}
