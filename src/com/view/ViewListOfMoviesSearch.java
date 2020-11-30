package com.view;

import com.modele.ListOfMoviesSearch;
import com.modele.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewListOfMoviesSearch extends JPanel implements Observer {

    ListOfMoviesSearch modele;

    public ViewListOfMoviesSearch(ListOfMoviesSearch modele) {
        this.modele = modele;
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        this.add(new JLabel("Resultats de la recherche : " + modele.getString()));
        int n = 0;
        for (Movie movie : modele.getMovies()) {
            c.gridx = n;
            c.gridy = 1;
            this.add(new ViewMovieSearch(movie), c);
            n++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
