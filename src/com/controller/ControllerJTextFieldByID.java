package com.controller;

import com.modele.Movie;
import com.view.ViewError;
import com.view.ViewMovie;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The type Controller j text field by id.
 */
public class ControllerJTextFieldByID implements KeyListener {

    /**
     * The Modele.
     */
    Movie modele;

    /**
     * The View.
     */
    ViewMovie view;

    /**
     * Instantiates a new Controller j text field by id.
     *
     * @param modele the modele
     * @param view   the view
     */
    public ControllerJTextFieldByID(Movie modele, ViewMovie view) {
        this.modele = modele;
        this.view = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getSource() == view.jLabelNumFilm) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    int numMovie = Integer.parseInt(view.jLabelNumFilm.getText());
                    modele.update(numMovie, 0);
                } catch (Exception exception) {
                    new ViewError("Veuillez ressaisir un id correct !");
                    modele.update(1, 0);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
