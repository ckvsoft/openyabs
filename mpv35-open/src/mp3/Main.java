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
package mp3;

import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import java.io.File;

import javax.swing.UIManager;
import mp3.installer.MpInstaller;
import mp3.classes.interfaces.Constants;
import mp3.classes.interfaces.Strings;
import mp3.classes.layer.Popup;
import mp3.classes.utils.FileReaderWriter;
import mp3.classes.utils.Log;
import mp3.classes.utils.SplashScreen;
import mp3.classes.visual.main.mainframe;

/**
 *
 * @author anti43
 */
public class Main implements Strings{
    private static SplashScreen splash;
    private static String VERSION= Constants.VERSION;

  
    /**
     * 
     * @param args
     */
    public Main(String[] args){
    
       try {
            UIManager.setLookAndFeel(new TinyLookAndFeel());
        } catch (Exception exe) {
            exe.printStackTrace();
        }
        
        splash = new SplashScreen(TEST_CONF);
 
        try {


            File df = new File(Constants.SETTINGS);
            if (df.exists()) {
            } else {

                if (df.mkdirs()) {
                    FileReaderWriter f = new FileReaderWriter(Constants.SETTINGS);
                    f.write(Constants.DATABASEPATH + COLON + VERSION);

                } else {
                    Popup.notice(PERMISSION_DENIED);
                    System.err.println(PERMISSION_DENIED);
                    System.exit(1);
                }
            }

            
            
            FileReaderWriter f = new FileReaderWriter(Constants.SETTINGS);

            String[] dat = f.read().split(COLON);

            String db = dat[0] + File.separator + Constants.DATABASENAME;
            File test = new File(db);
            if (test.exists()) {
                
                try {
                    if (args.length > 0 && args[0].endsWith("verbose")) {
                        Log.setLogLevel(Log.LOGLEVEL_HIGH);
                    } else {

                        Log.setLogLevel(Log.LOGLEVEL_LOW);

                    }

                } catch (Exception exception) {
                      Log.setLogLevel(Log.LOGLEVEL_LOW);
                }
//                  Log.setLogLevel(Log.LOGLEVEL_HIGH);
                
                try {
                    splash.setComp(new mainframe(splash, this));

                } catch (Exception exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }

                
                
            } else {
                splash.setComp(new MpInstaller());
            }
        } catch (Exception exception) {

            Log.Debug(exception);
        }
    
    }

    public static void main(String[] args) {

        new Main(args);

    }

}