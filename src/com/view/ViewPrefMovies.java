package com.view;

import com.modele.ListOfMovies;
import com.modele.Movie;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ViewPrefMovies extends JPanel implements Observer {

    ListOfMovies listOfMovies;

    public JButton prefA = new JButton("Je prefère A");

    public JButton choix = new JButton("Je choisis A");

    public JButton prefB = new JButton("Je prefère B");

    public JButton choixB = new JButton("Je choisis B");

    public ViewPrefMovies(ListOfMovies movies) {
        this.listOfMovies = movies;
        this.setLayout(new GridLayout(1, 2));
        this.add(initA(movies.getFirstElement()));
        this.add(initB(movies.leastSimular()));
    }

    JPanel initA(int id) {
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(new ViewMovieAB(new Movie(id)), BorderLayout.CENTER);
        JPanel jpbtn = new JPanel(new GridLayout(1, 2));
        jPanel.add(jpbtn, BorderLayout.SOUTH);
        jpbtn.add(choix);
        jpbtn.add(prefA);
        return jPanel;
    }

    JPanel initB(int id) {
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(new ViewMovieAB(new Movie(id)), BorderLayout.CENTER);
        JPanel jpbtn = new JPanel(new GridLayout(1, 2));
        jPanel.add(jpbtn, BorderLayout.SOUTH);
        jpbtn.add(prefB);
        jpbtn.add(choixB);
        return jPanel;
    }

    public void setListOfMovies(ListOfMovies listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
