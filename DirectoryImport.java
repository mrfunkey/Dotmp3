package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import logic.Handler;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;


public class DirectoryImport extends JFrame implements ActionListener {
    private JPanel panel;
    private GridBagConstraints gbc;
    private JLabel selectDirectoryLabel;
    private JButton selectDirectoryButton;
    private JButton submitButton;

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;


    public DirectoryImport() {
        setTitle("Import Directory");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.CENTER;

        selectDirectoryLabel = new JLabel("Select an mp3 directory to import");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 0, 10, 0);
        panel.add(selectDirectoryLabel, gbc);

        selectDirectoryButton = new JButton("File");
        selectDirectoryButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(selectDirectoryButton, gbc);

        String[] columnTitles = {"Title", "Artist", "Album", "Year"};
        tableModel = new DefaultTableModel(columnTitles, 0);
        table =  new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 400));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(scrollPane, gbc);

        submitButton = new JButton("Submit");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.fill = GridBagConstraints.NONE;
        panel.add(submitButton, gbc);


        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectDirectoryButton) {
            File userDirectory = Handler.handleUserDirectory(this);
            if (userDirectory != null) {
                File[] files = userDirectory.listFiles();

                tableModel.setRowCount(0);

                if(files != null){
                    for (File f : files) {
                        if (f.getName().toLowerCase().endsWith(".mp3")) {
                            try{
                                org.jaudiotagger.audio.AudioFile audioFile = AudioFileIO.read(f);
                                org.jaudiotagger.tag.Tag tag = audioFile.getTag();

                                String title =  tag.getFirst(FieldKey.TITLE);
                                String artist =  tag.getFirst(FieldKey.ARTIST);
                                String album =  tag.getFirst(FieldKey.ALBUM);
                                String year =  tag.getFirst(FieldKey.YEAR);

                                tableModel.addRow(new Object[]{title,artist,album,year});
                            }
                            catch (Exception ex){

                            }


                        }
                    }
                }
            }
        }
    }
}
