package com.modele;

import com.activerecord.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type List of movies search.
 */
public class ListOfMoviesSearch extends Modele {

    /**
     * The Movies.
     */
    ArrayList<Movie> movies = new ArrayList<>();
    /**
     * The String.
     */
    String string = "";

    /**
     * Add movie to list with title int.
     *
     * @param selection the selection
     * @return the int
     */
    public int addMovieToListWithTitle(String selection) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement sql2 = connection.prepareStatement("select * from movie where title LIKE ?");
            return prepareSQLStatement(selection, sql2);
        } catch (SQLException | ClassNotFoundException throwables) {
            return 1;
        }
    }

    private int prepareSQLStatement(String selection, PreparedStatement sql2) throws SQLException {
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
    }


    /**
     * Add movie to list all int.
     *
     * @param selection the selection
     * @return the int
     */
    public int addMovieToListAll(String selection) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement sql2 = connection.prepareStatement("select distinct mov_id from movie natural join people natural join role where concat_ws('|',movie.title,movie.mov_id,movie.release_date,movie.genres, movie.type, movie.synopsis, people.peo_name, people.peo_id, role.mov_id, role.rol_name) LIKE ?");
            return prepareSQLStatement(selection, sql2);
        } catch (SQLException | ClassNotFoundException throwables) {
            return 1;
        }
    }

    /**
     * Gets movies.
     *
     * @return the movies
     */
    public ArrayList<Movie> getMovies() {
        return movies;
    }

    @Override
    public void update() {
        super.update();
    }

    /**
     * Gets string.
     *
     * @return the string
     */
    public String getString() {
        return string;
    }
}
