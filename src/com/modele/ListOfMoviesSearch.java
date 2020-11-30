package com.modele;

import com.activerecord.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListOfMoviesSearch extends Modele {

    ArrayList<Movie> movies = new ArrayList<>();
    String string = "";

    public int addMovieToListWithTitle(String selection) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement sql2 = connection.prepareStatement("select * from movie where title LIKE ?");
            sql2.setString(1, "%" + selection + "%");
            ResultSet rs2 = sql2.executeQuery();
            while (rs2.next()) {
                this.movies.add(new Movie(rs2.getInt(1)));
            }
            this.string = selection;
            if (this.movies.isEmpty())
                return 1;
            else
                return 0;
        } catch (SQLException | ClassNotFoundException throwables) {
            return 1;
        }
    }


    public int addMovieToListAll(String selection) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement sql2 = connection.prepareStatement("select * from movie where title LIKE ? OR genres LIKE ? OR type LIKE ? OR synopsis LIKE ?");
            sql2.setString(1, "%" + selection + "%");
            sql2.setString(2, "%" + selection + "%");
            sql2.setString(3, "%" + selection + "%");
            sql2.setString(4, "%" + selection + "%");
            ResultSet rs2 = sql2.executeQuery();
            while (rs2.next()) {
                this.movies.add(new Movie(rs2.getInt(1)));
            }
            this.string = selection;
            if (this.movies.isEmpty())
                return 1;
            else
                return 0;
        } catch (SQLException | ClassNotFoundException throwables) {
            return 1;
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    @Override
    public void update() {
        super.update();
    }

    public String getString() {
        return string;
    }
}
