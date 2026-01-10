package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import logic.Handler;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.images.Artwork;

public class Mp3Import extends JFrame implements ActionListener {
    private JPanel panel;
    private GridBagConstraints gbc;
    private JLabel selectFileLabel;
    private JButton fileButton;
    private JLabel artLabel;
    private String title;
    private String artist;
    private String album;
    private String year;
    private JLabel titleLabel;
    private JLabel artistLabel;
    private JLabel albumLabel;
    private JLabel yearLabel;


    public Mp3Import(){
        setTitle("Import .mp3 File");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();

        selectFileLabel = new JLabel("Select a .mp3 file to import");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 10, 0);
        panel.add(selectFileLabel, gbc);

        fileButton = new JButton("File");
        fileButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(fileButton, gbc);

        gbc.gridy = 2;
        gbc.weighty = 1.0;
        panel.add(new JLabel(""), gbc);

        artLabel = new JLabel("Artwork");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 0, 5, 0);
        panel.add(artLabel, gbc);

        titleLabel = new JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 3, 0);
        panel.add(titleLabel, gbc);

        artistLabel = new  JLabel("");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(artistLabel, gbc);

        add(panel);
        setVisible(true);
    }


    public void actionPerformed(ActionEvent e){
        if (e.getSource() == fileButton){
            File userFile = Handler.handleUserMp3(this);
            if (userFile != null){
                try{
                    AudioFile mp3File = AudioFileIO.read(userFile);
                    Tag tag = mp3File.getTag();

                    title = tag.getFirst(FieldKey.TITLE);
                    titleLabel.setText("Song: " + title);

                    artist = tag.getFirst(FieldKey.ARTIST);
                    artistLabel.setText("Artist: " + artist);

                    album = tag.getFirst(FieldKey.ALBUM);
                    year = tag.getFirst(FieldKey.YEAR);

                    Artwork mp3Artwork = tag.getFirstArtwork();
                    if (mp3Artwork != null){
                        byte[] bytes = mp3Artwork.getBinaryData();

                        ImageIcon imageIcon = new ImageIcon(bytes);

                        Image scaledImage = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);

                        artLabel.setIcon(new ImageIcon(scaledImage));
                        artLabel.setText("");
                    }
                }
                catch (Exception ex){
                    JOptionPane.showMessageDialog(this,
                            "Error while reading mp3 file.",
                            "Error", JOptionPane.ERROR_MESSAGE
                    );
                }



            }

        }
    }
}
