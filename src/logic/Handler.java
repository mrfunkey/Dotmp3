package logic;

import GUI.Mp3Import;

import javax.swing.*;
import java.io.File;

public class Handler {

    public static void handleMp3Import(){
        Mp3Import m = new Mp3Import();
        m.setVisible(true);

    }

    public static File handleUserMp3(JFrame mp3Import){
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnVal = chooser.showOpenDialog(mp3Import);

        if (returnVal == JFileChooser.APPROVE_OPTION){
            return chooser.getSelectedFile();
        }
        return null;
    }

    public static void handleDirectoryImport(){


    }


}
