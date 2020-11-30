package com.view;

import com.controller.ControllerParticipants;
import com.controller.ControllerPrefMovies;
import com.modele.ListOfMovies;
import com.modele.Movie;
import com.modele.Participants;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Observable;
import java.util.Observer;

public class ViewMovie extends JPanel implements Observer {

    Movie modele;
    public ListOfMovies listOfMovies;

    public JButton researchByAlgo = new JButton("Rerchercher un film");

    public JTextField textFieldRechercheT = new JTextField("Recherche par titre");
    public JButton jButtonRechercheT = new JButton("Go");

    public JTextField textFieldRechercheN = new JTextField("Recherche par nom (acteur, ...)");
    public JButton jButtonRechercheN = new JButton("Go");

    public JButton prev = new JButton("Précédent");
    public JButton suiv = new JButton("Suivant");
    public JTextField jLabelNumFilm = new JTextField("1");

    JLabel titreFilm;

    JLabel genreFilm;

    JTextArea synopsisFilm;

    ImageIcon afficheFilm = new ImageIcon();

    JLabel yearFilm;
    JLabel noteFilm;

    //public ArrayList<JButton> listeParticipants = new ArrayList<>();

    public JPanel jPanelParticipants;

    public ViewMovie(Movie modele) {
        listOfMovies = new ListOfMovies();
        ControllerPrefMovies controllerPrefMovies = new ControllerPrefMovies(listOfMovies, this);
        this.researchByAlgo.addActionListener(controllerPrefMovies);
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
        this.add(researchByAlgo, c);
        c.gridy = 1;
        this.add(initRechercheT(), c);
        c.gridy = 2;
        this.add(initRechercheN(), c);
        c.gridy = 3;
        this.add(initPrevSuiv(), c);
        c.gridy = 4;
        this.initLabelTitre();
        this.add(titreFilm, c);
        c.gridy = 5;
        c.weightx = 1;
        c.weighty = 1;
        this.add(initAfficheFilm(), c);
        this.initLabelGenres();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 6;
        this.add(genreFilm, c);
        this.initLabelSynopsis();
        c.gridy = 7;
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
        this.titreFilm.setFont(new Font("", Font.PLAIN, 26));
    }

    private void initLabelSynopsis() {
        this.synopsisFilm.setOpaque(true);
        this.synopsisFilm.setBackground(Color.cyan);
        this.synopsisFilm.setForeground(Color.white);
        this.synopsisFilm.setLineWrap(true);
        this.synopsisFilm.setWrapStyleWord(true);
        this.synopsisFilm.setEditable(false);
        this.synopsisFilm.setMargin(new Insets(5,5,5,5));
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

    private JPanel initRechercheT() {
        //colors
        this.jButtonRechercheT.setBackground(Color.black);
        this.jButtonRechercheT.setForeground(Color.white);
        this.researchByAlgo.setBackground(Color.black);
        this.researchByAlgo.setForeground(Color.white);
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.gray);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(textFieldRechercheT, BorderLayout.CENTER);
        jPanel.add(jButtonRechercheT, BorderLayout.EAST);
        //jButtonRechercheT.addActionListener(new ControllerRechercheTitre(listOfMovies,this));
        return jPanel;
    }

    private JPanel initRechercheN() {
        //colors
        this.jButtonRechercheN.setBackground(Color.black);
        this.jButtonRechercheN.setForeground(Color.white);
        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.gray);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(textFieldRechercheN, BorderLayout.CENTER);
        jPanel.add(jButtonRechercheN, BorderLayout.EAST);
        return jPanel;
    }

    private JPanel initPrevSuiv() {
        //colors
        this.prev.setBackground(Color.black);
        this.suiv.setBackground(Color.black);
        this.prev.setForeground(Color.white);
        this.suiv.setForeground(Color.white);

        this.noteFilm.setBackground(Color.white);
        this.noteFilm.setOpaque(true);
        this.jLabelNumFilm.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(Color.gray);
        jPanel.setLayout(new GridLayout(1, 3));
        jPanel.add(prev);
        jPanel.add(jLabelNumFilm);
        jPanel.add(suiv);
        return jPanel;
    }

    private JPanel initAfficheFilm() {
        JPanel jPanel = new JPanel();
       // jPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        jPanel.setLayout(new BorderLayout());

        JPanel jPanelEAST = new JPanel();
        //jPanelEAST.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        c.fill = GridBagConstraints.BOTH;
        c.gridy = y;
        JButton button = new JButton(text);
        button.setBackground(Color.orange);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button, c);
        //listeParticipants.add(button);
        button.addActionListener(new ControllerParticipants(modele, this));
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
        jLabelNumFilm.setText(String.valueOf(modele.getMov_id()));
        titreFilm.setText(modele.getTitle());
        yearFilm.setText(modele.getRelease_date());
        noteFilm.setText(String.valueOf(modele.getRating()));
        genreFilm.setText(modele.getGenres());
        synopsisFilm.setText(modele.getSynopsis());
        updateImage();
        jPanelParticipants.removeAll();
        createParcipants(jPanelParticipants);
    }
}
