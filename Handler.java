package logic;

import GUI.DirectoryImport;
import GUI.Mp3Import;

import javax.swing.*;
import java.io.File;

public class Handler {



    public static File handleUserMp3(JFrame mp3Import){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (fileChooser.showOpenDialog(mp3Import) == JFileChooser.APPROVE_OPTION){
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    public static File handleUserDirectory(JFrame directoryImport){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (fileChooser.showOpenDialog(directoryImport) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();

        }
        return null;
    }

    public static void handleMp3Import(){
        Mp3Import m = new Mp3Import();
        m.setVisible(true);

    }

    public static void handleDirectoryImport(){
        DirectoryImport d = new DirectoryImport();
        d.setVisible(true);

    }


}
