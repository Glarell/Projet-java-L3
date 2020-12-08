package com.controller;

import com.modele.Movie;
import com.view.View;
import com.view.ViewError;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Controller prev next.
 */
public class ControllerPrevNext implements ActionListener {

    /**
     * The Modele.
     */
    Movie modele;

    /**
     * The View.
     */
    View view;

    /**
     * Instantiates a new Controller prev next.
     *
     * @param modele the modele
     * @param view   the view
     */
    public ControllerPrevNext(Movie modele, View view) {
        this.modele = modele;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == view.viewMovie.suiv) {
                // System.out.println("Film suivant !");
                int numMovie = Integer.parseInt(view.viewMovie.jLabelNumFilm.getText());
                numMovie = numMovie + 1;
                modele.update(numMovie, 0);
            } else if (e.getSource() == view.viewMovie.prev) {
                //System.out.println("Film précédent !");
                int numMovie = Integer.parseInt(view.viewMovie.jLabelNumFilm.getText());
                numMovie = numMovie - 1;
                modele.update(numMovie, 1);
            }
        } catch (Exception exception) {
            new ViewError("Veuillez ressaisir un id correct !");
            modele.update(1, 0);
        }
    }
}
