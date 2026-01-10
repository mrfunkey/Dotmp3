package GUI;

import logic.Handler;

import javax.swing.*;
import javax.swing.JMenuBar;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Mainframe extends JFrame implements PanelSwitcher, ActionListener {

    private CardLayout cardLayout;
    private JPanel cardPanel;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu helpMenu;
    JMenu importSubMenu;
    JMenuItem mp3;
    JMenuItem directory;


    public Mainframe (){
        setTitle("Dotmp3");
        setSize(600,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        importSubMenu = new JMenu("Import");

        mp3 = new JMenuItem("mp3");
        mp3.addActionListener(this);

        directory = new JMenuItem("Directory");
        directory.addActionListener(this);

        importSubMenu.add(mp3);
        importSubMenu.add(directory);

        fileMenu.add(importSubMenu);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        add(menuBar);



        setJMenuBar(menuBar);


        cardLayout = new CardLayout();

        cardPanel = new JPanel(cardLayout);
        cardPanel.setLayout(cardLayout);

        cardPanel.add(new Homepage(this), "Homepage Panel");

        add(cardPanel);
        setVisible(true);
    }

    @Override
    public void showPanel(String visiblePanel){
        cardLayout.show(cardPanel, visiblePanel);
    }

    public void actionPerformed(ActionEvent e){
        String  command = e.getActionCommand();

        switch (command){
            case "mp3":
                Handler.handleMp3Import();
                break;
            case "Directory":
                Handler.handleDirectoryImport();
                break;
        }
    }
}
