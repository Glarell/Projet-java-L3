package com.view;

import com.modele.Movie;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    Movie modele;
    public ViewMovie viewMovie;

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

    public void setViewMovie(ViewMovie viewMovie) {
        this.viewMovie = viewMovie;
    }

    @Override
    public void update(Observable o, Object arg) {
        viewMovie.repaint();
    }

    public ViewMovie getViewMovie() {
        return viewMovie;
    }
}
