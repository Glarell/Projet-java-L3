package com.controller;

import com.modele.ListOfMovies;
import com.view.View;
import com.view.ViewError;
import com.view.ViewMovie;
import com.view.ViewPrefMovies;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPrefChoose implements ActionListener {


    ListOfMovies modele;

    ViewPrefMovies view;

    public ControllerPrefChoose(ListOfMovies modele, ViewPrefMovies view) {
        this.modele = modele;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == view.choixB) {
                View fenetre = (View) SwingUtilities.getWindowAncestor(view);
                ViewMovie viewMovie = new ViewMovie(modele.getMovieB());
                fenetre.setViewMovie(viewMovie);
                ControllerPrevNext controllerNext = new ControllerPrevNext(modele.getMovieB(), fenetre);
                fenetre.getViewMovie().suiv.addActionListener(controllerNext);
                fenetre.getViewMovie().prev.addActionListener(controllerNext);
                fenetre.setContentPane(viewMovie);
                modele.getMovieB().update();
                fenetre.validate();
                fenetre.pack();
            } else if (e.getSource() == view.choix) {
                View fenetre = (View) SwingUtilities.getWindowAncestor(view);
                ViewMovie viewMovie = new ViewMovie(modele.getMovieA());
                fenetre.setViewMovie(viewMovie);
                ControllerPrevNext controllerNext = new ControllerPrevNext(modele.getMovieA(), fenetre);
                fenetre.getViewMovie().suiv.addActionListener(controllerNext);
                fenetre.getViewMovie().prev.addActionListener(controllerNext);
                fenetre.setContentPane(viewMovie);
                modele.getMovieA().update();
                fenetre.validate();
                fenetre.pack();
            } else if (e.getSource() == view.prefA) {
                modele.divideByBetter();
                boolean active = true;
                if (this.modele.getSize() == 2) {
                    active = false;
                }
                View fenetre = (View) SwingUtilities.getWindowAncestor(view);
                ViewPrefMovies viewPref = new ViewPrefMovies(modele);
                viewPref.prefB.setEnabled(active);
                viewPref.prefA.setEnabled(active);
                ControllerPrefChoose controllerPrefChoose = new ControllerPrefChoose(modele, viewPref);
                viewPref.prefB.addActionListener(controllerPrefChoose);
                viewPref.prefA.addActionListener(controllerPrefChoose);
                viewPref.choix.addActionListener(controllerPrefChoose);
                viewPref.choixB.addActionListener(controllerPrefChoose);
                fenetre.setContentPane(viewPref);
                fenetre.pack();
            } else if (e.getSource() == view.prefB) {
                modele.divideByWorst();
                boolean active = true;
                System.out.println(this.modele.getSize());
                if (this.modele.getSize() <= 2) {
                    active = false;
                }
                View fenetre = (View) SwingUtilities.getWindowAncestor(view);
                ViewPrefMovies viewPref = new ViewPrefMovies(modele);
                viewPref.prefB.setEnabled(active);
                viewPref.prefA.setEnabled(active);
                ControllerPrefChoose controllerPrefChoose = new ControllerPrefChoose(modele, viewPref);
                viewPref.prefB.addActionListener(controllerPrefChoose);
                viewPref.prefA.addActionListener(controllerPrefChoose);
                viewPref.choix.addActionListener(controllerPrefChoose);
                viewPref.choixB.addActionListener(controllerPrefChoose);
                fenetre.setContentPane(viewPref);
                fenetre.pack();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            new ViewError("Veuillez ressaisir un id correct !");
        }
    }
}
