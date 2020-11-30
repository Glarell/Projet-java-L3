package com.controller;

import com.modele.Movie;
import com.view.View;
import com.view.ViewError;
import com.view.ViewMovie;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControllerJTextFieldByID implements KeyListener {

    Movie modele;

    ViewMovie view;

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
