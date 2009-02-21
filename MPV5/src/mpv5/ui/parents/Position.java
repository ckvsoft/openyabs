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
package mpv5.ui.parents;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 *
 * @author anti43
 */
public class Position {
    private Component comp;

    public Position(Component comp) {
        this.comp = comp;
        center();
    }

    public void center() {
        Dimension frameSize = new Dimension(comp.getSize());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int top = (screenSize.height - frameSize.height) / 2;
        int left = (screenSize.width - frameSize.width) / 2;

        comp.setSize(frameSize);
        comp.setLocation(left, top);
    }


    public boolean isNotMaximized() {
        Dimension frameSize = new Dimension(comp.getSize());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        if (frameSize.width < screenSize.width) {
            return true;
        } else {
            return false;
        }
    }

    public void topLeft() {
        comp.setLocation(0, 0);
    }

}

