package com.controller;

import com.modele.Movie;
import com.modele.Personnage;
import com.view.ViewMovie;
import com.view.ViewParticipant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ControllerParticipants implements ActionListener {

    Movie modele;

    ViewMovie view;

    public ControllerParticipants(Movie modele, ViewMovie view) {
        this.modele = modele;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            JButton temp = (JButton) e.getSource();
            String nameP = temp.getText().substring(0, temp.getText().indexOf(":"));
            //select peo_id from people where peo_name = 'john lasseter';
            JFrame fenetre = (JFrame) SwingUtilities.getWindowAncestor(view);
            Personnage modele = new Personnage(Personnage.getPeopleByName(nameP));
            ViewParticipant vpers = new ViewParticipant(modele);
            JScrollPane scrollPane = new JScrollPane(vpers);
            fenetre.setContentPane(scrollPane);
            fenetre.validate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
