package com.view;

import com.modele.Movie;
import com.modele.Participants;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;
import java.util.Observer;

public class ViewMovieAB extends JPanel implements Observer {

    Movie modele;

    JLabel titreFilm;

    JLabel genreFilm;

    JTextArea synopsisFilm;

    ImageIcon afficheFilm = new ImageIcon();

    JLabel yearFilm;
    JLabel noteFilm;

    public JPanel jPanelParticipants;

    public ViewMovieAB(Movie modele) {
        this.modele = modele;
        titreFilm = new JLabel(modele.getTitle());
        updateImage();
        genreFilm = new JLabel(modele.getGenres());
        synopsisFilm = new JTextArea(modele.getSynopsis(), 5, 5);
        yearFilm = new JLabel(modele.getRelease_date());
        noteFilm = new JLabel(String.valueOf(modele.getRating()));
        modele.addObserver(this);
        this.setBackground(Color.gray);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        this.initLabelTitre();
        this.add(titreFilm, c);
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        this.add(initAfficheFilm(), c);
        this.initLabelGenres();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 2;
        this.add(genreFilm, c);
        this.initLabelSynopsis();
        c.gridy = 3;
        this.add(synopsisFilm, c);
        createParcipants(jPanelParticipants);
    }


    private void initLabelTitre() {
        this.genreFilm.setOpaque(true);
        this.genreFilm.setBackground(Color.blue);
        this.genreFilm.setForeground(Color.white);
        this.genreFilm.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initLabelGenres() {
        this.titreFilm.setOpaque(true);
        this.titreFilm.setBackground(Color.blue);
        this.titreFilm.setForeground(Color.white);
        this.titreFilm.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void initLabelSynopsis() {
        this.synopsisFilm.setOpaque(true);
        this.synopsisFilm.setBackground(Color.cyan);
        this.synopsisFilm.setForeground(Color.white);
        this.synopsisFilm.setLineWrap(true);
        this.synopsisFilm.setWrapStyleWord(true);
        this.synopsisFilm.setEditable(false);
    }

    private void initYearNote() {
        this.noteFilm.setBackground(Color.white);
        this.noteFilm.setOpaque(true);
        this.yearFilm.setOpaque(true);
        this.yearFilm.setBackground(Color.orange);
        this.yearFilm.setForeground(Color.white);
        this.yearFilm.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.noteFilm.setOpaque(true);
        this.noteFilm.setBackground(Color.orange);
        this.noteFilm.setForeground(Color.white);
        this.noteFilm.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private JPanel initAfficheFilm() {
        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        jPanel.setLayout(new BorderLayout());

        JPanel jPanelEAST = new JPanel();
        jPanelEAST.setBorder(new EmptyBorder(10, 10, 10, 10));
        jPanelEAST.setLayout(new BoxLayout(jPanelEAST, BoxLayout.Y_AXIS));
        this.initYearNote();
        jPanelEAST.add(this.yearFilm);
        jPanelEAST.add(this.noteFilm);

        JPanel jPanelPartcipant = new JPanel(new GridBagLayout());
        //jPanelPartcipant.setLayout(new BoxLayout(jPanelPartcipant, BoxLayout.Y_AXIS));
        jPanelPartcipant.setBackground(Color.blue);

        JScrollPane jScrollPane = new JScrollPane(jPanelPartcipant);
        //createParcipants(jPanelPartcipant);

        jPanelEAST.add(jScrollPane);
        JPanel ouest = new JPanel(new BorderLayout());
        JLabel pic = new JLabel(this.afficheFilm);
        ouest.add(pic, BorderLayout.CENTER);

        jPanel.add(ouest, BorderLayout.CENTER);
        jPanel.add(jPanelEAST, BorderLayout.EAST);
        this.jPanelParticipants = jPanelPartcipant;
        return jPanel;
    }

    private void createParcipants(JPanel jPanel) {
        Participants participants = new Participants(modele.getMov_id());
        for (int i = 0; i < participants.getListeP().size(); i++) {
            addAButtonGridBagLayout(participants.getListeP().get(i).getName() + " : " + participants.getListeP().get(i).getRole(), jPanel, i + 1);
        }
    }

    private void addAButtonGridBagLayout(String text, Container container, int y) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = y;
        JButton button = new JButton(text);
        button.setBackground(Color.orange);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(new EmptyBorder(10, 10, 10, 10));
        container.add(button, c);
    }

    void updateImage() {
        BufferedImage image;
        try {
            URL url1 = new URL(modele.getPoster());
            URLConnection connection = url1.openConnection();
            connection.setRequestProperty("User-Agent", "xxxxxx");
            image = ImageIO.read(url1);
            this.afficheFilm.setImage(image);
        } catch (IOException e) {
            try {
                image = ImageIO.read(new File("D:\\Cours_IDMC\\ProjetJava\\src\\com\\image_not_found.png"));
                this.afficheFilm.setImage(image);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
