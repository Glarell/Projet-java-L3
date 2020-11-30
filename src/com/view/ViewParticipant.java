package com.view;

import com.controller.ControllerParticipantFilm;
import com.modele.Movie;
import com.modele.Personnage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ViewParticipant extends JPanel implements Observer {

    Personnage personnage;

    JLabel nomPersonne;

    public ViewParticipant(Personnage pers) {
        personnage = pers;
        this.setLayout(new BorderLayout());
        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridy = 0;
        c.weightx = 0;
        this.setBackground(Color.gray);
        this.nomPersonne = new JLabel(pers.getPeo_name());
        nomPersonne.setOpaque(true);
        nomPersonne.setBackground(Color.cyan);
        nomPersonne.setForeground(Color.white);
        nomPersonne.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(this.nomPersonne, BorderLayout.NORTH);
        int n = 0;
        for (ArrayList<String> arrayList : personnage.films) {
            int y = 1;
            c.gridx = n;
            c.gridy = y;
            for (int i = 0; i < arrayList.size(); i++) {
                JLabel temp = new JLabel();
                if (i == 0) {
                    try {
                        BufferedImage image;
                        URL url1 = new URL(arrayList.get(i));
                        URLConnection connection = url1.openConnection();
                        connection.setRequestProperty("User-Agent", "xxxxxx");
                        image = ImageIO.read(url1);
                        temp.setIcon(new ImageIcon(image));
                    } catch (Exception e) {
                        temp.setIcon(new ImageIcon("D:\\Cours_IDMC\\ProjetJava\\src\\com\\image_not_found.png"));
                    }
                    Movie movie = new Movie(Integer.parseInt(arrayList.get(4)));
                    ControllerParticipantFilm controllerParticipantFilm = new ControllerParticipantFilm(movie, temp);
                    temp.addMouseListener(controllerParticipantFilm);
                } else {
                    temp.setText(arrayList.get(i));
                }
                if (i == 1) {
                    temp.setOpaque(true);
                    temp.setBackground(Color.blue);
                    temp.setForeground(Color.white);
                    temp.setHorizontalAlignment(SwingConstants.CENTER);
                } else if (i == 2) {
                    temp.setOpaque(true);
                    temp.setBackground(Color.orange);
                    temp.setForeground(Color.white);
                    temp.setHorizontalAlignment(SwingConstants.CENTER);
                } else if (i == 3) {
                    temp.setOpaque(true);
                    temp.setBackground(Color.green);
                    temp.setForeground(Color.white);
                    temp.setHorizontalAlignment(SwingConstants.CENTER);
                }
                jPanel.add(temp, c);
                y++;
                c.gridy = y;
                n++;
            }
        }
        this.add(jPanel, BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
