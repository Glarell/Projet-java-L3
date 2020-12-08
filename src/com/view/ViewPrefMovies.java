package com.view;

import com.modele.ListOfMovies;
import com.modele.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The type View pref movies.
 */
public class ViewPrefMovies extends JPanel implements Observer {

    /**
     * The List of movies.
     */
    ListOfMovies listOfMovies;

    /**
     * The Pref a.
     */
    public JButton prefA = new JButton("Je prefère A");

    /**
     * The Choix.
     */
    public JButton choix = new JButton("Je choisis A");

    /**
     * The Pref b.
     */
    public JButton prefB = new JButton("Je prefère B");

    /**
     * The Choix b.
     */
    public JButton choixB = new JButton("Je choisis B");

    /**
     * Instantiates a new View pref movies.
     *
     * @param movies the movies
     */
    public ViewPrefMovies(ListOfMovies movies) {
        this.listOfMovies = movies;
        this.setLayout(new GridLayout(1, 2));
        this.add(initA(movies.getFirstElement()));
        this.add(initB(movies.leastSimular()));
    }

    /**
     * Init a j panel.
     *
     * @param id the id
     * @return the j panel
     */
    JPanel initA(int id) {
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(new ViewMovieAB(new Movie(id)), BorderLayout.CENTER);
        JPanel jpbtn = new JPanel(new GridLayout(1, 2));
        jPanel.add(jpbtn, BorderLayout.SOUTH);
        jpbtn.add(choix);
        jpbtn.add(prefA);
        return jPanel;
    }

    /**
     * Init b j panel.
     *
     * @param id the id
     * @return the j panel
     */
    JPanel initB(int id) {
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(new ViewMovieAB(new Movie(id)), BorderLayout.CENTER);
        JPanel jpbtn = new JPanel(new GridLayout(1, 2));
        jPanel.add(jpbtn, BorderLayout.SOUTH);
        jpbtn.add(prefB);
        jpbtn.add(choixB);
        return jPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
