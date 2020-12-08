package com.controller;

import com.modele.ListOfMovies;
import com.view.View;
import com.view.ViewMovie;
import com.view.ViewPrefMovies;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Controller pref movies.
 */
public class ControllerPrefMovies implements ActionListener {


    /**
     * The Modele.
     */
    ListOfMovies modele;

    /**
     * The View.
     */
    ViewMovie view;

    /**
     * Instantiates a new Controller pref movies.
     *
     * @param modele the modele
     * @param view   the view
     */
    public ControllerPrefMovies(ListOfMovies modele, ViewMovie view) {
        this.modele = modele;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        View fenetre = (View) SwingUtilities.getWindowAncestor(view);
        ViewPrefMovies viewPrefMovies = new ViewPrefMovies(modele);
        ControllerPrefChoose controllerPrefChoose = new ControllerPrefChoose(modele, viewPrefMovies);
        viewPrefMovies.prefB.addActionListener(controllerPrefChoose);
        viewPrefMovies.prefA.addActionListener(controllerPrefChoose);
        viewPrefMovies.choix.addActionListener(controllerPrefChoose);
        viewPrefMovies.choixB.addActionListener(controllerPrefChoose);
        fenetre.setContentPane(viewPrefMovies);
        fenetre.pack();
    }
}
