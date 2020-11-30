package com.controller;

import com.modele.ListOfMoviesSearch;
import com.modele.Movie;
import com.view.View;
import com.view.ViewMovie;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControllerListOfMoviesAffiche implements MouseListener {

    Movie modele;

    JLabel view;

    public ControllerListOfMoviesAffiche(Movie modele, JLabel view) {
        this.modele = modele;
        this.view = view;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        View fenetre = (View) SwingUtilities.getWindowAncestor(view);
        ViewMovie viewMovie = new ViewMovie(modele);
        fenetre.setViewMovie(viewMovie);
        ControllerPrevNext controllerNext = new ControllerPrevNext(modele, fenetre);
        fenetre.getViewMovie().suiv.addActionListener(controllerNext);
        fenetre.getViewMovie().prev.addActionListener(controllerNext);
        ControllerJTextFieldByID controllerJTextFieldByID = new ControllerJTextFieldByID(modele, viewMovie);
        viewMovie.jLabelNumFilm.addKeyListener(controllerJTextFieldByID);
        ControllerOkTitle controllerOkTitle = new ControllerOkTitle(new ListOfMoviesSearch(),viewMovie);
        viewMovie.jButtonRechercheT.addActionListener(controllerOkTitle);
        ControllerOkAll controllerOkAll = new ControllerOkAll(new ListOfMoviesSearch(),viewMovie);
        viewMovie.jButtonRechercheN.addActionListener(controllerOkAll);
        fenetre.setContentPane(viewMovie);
        modele.update();
        fenetre.validate();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
