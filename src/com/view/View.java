package com.view;

import com.modele.Movie;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The type View.
 */
public class View extends JFrame implements Observer {

    /**
     * The Modele.
     */
    Movie modele;
    /**
     * The View movie.
     */
    public ViewMovie viewMovie;

    /**
     * Instantiates a new View.
     *
     * @param modele the modele
     */
    public View(Movie modele) {
        this.modele = modele;
        viewMovie = new ViewMovie(modele);
        this.setContentPane(viewMovie);
        modele.addObserver(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(200, 200);
        pack();
        setVisible(true);
    }

    /**
     * Sets view movie.
     *
     * @param viewMovie the view movie
     */
    public void setViewMovie(ViewMovie viewMovie) {
        this.viewMovie = viewMovie;
    }

    @Override
    public void update(Observable o, Object arg) {
        viewMovie.repaint();
    }

    /**
     * Gets view movie.
     *
     * @return the view movie
     */
    public ViewMovie getViewMovie() {
        return viewMovie;
    }
}
