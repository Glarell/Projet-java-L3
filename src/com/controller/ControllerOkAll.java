package com.controller;

import com.modele.ListOfMoviesSearch;
import com.view.View;
import com.view.ViewError;
import com.view.ViewListOfMoviesSearch;
import com.view.ViewMovie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The type Controller ok all.
 */
public class ControllerOkAll implements ActionListener {


    /**
     * The Modele.
     */
    ListOfMoviesSearch modele;
    /**
     * The View.
     */
    ViewMovie view;

    /**
     * Instantiates a new Controller ok all.
     *
     * @param modele the modele
     * @param view   the view
     */
    public ControllerOkAll(ListOfMoviesSearch modele, ViewMovie view) {
        this.modele = modele;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = view.textFieldRechercheN.getText();
        switch (modele.addMovieToListAll(text)) {
            case 0:
                View fenetre = (View) SwingUtilities.getWindowAncestor(view);
                ViewListOfMoviesSearch viewPrefMovies = new ViewListOfMoviesSearch(modele);
                JScrollPane scrollPane = new JScrollPane(viewPrefMovies);
                fenetre.setContentPane(scrollPane);
                fenetre.validate();
                break;
            case 1:
                new ViewError("La recherche ne correspond à aucun résultat ...");
                break;
        }
    }
}
