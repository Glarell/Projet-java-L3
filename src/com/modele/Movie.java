package com.modele;

import com.activerecord.ConnectionSingleton;
import com.view.ViewError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * The type Movie.
 */
public class Movie extends Modele {

    /**
     * The Mov id.
     */
    int mov_id;
    /**
     * The Imdb id.
     */
    int imdb_id;
    /**
     * The Title.
     */
    String title;
    /**
     * The Poster.
     */
    String poster;
    /**
     * The Genres.
     */
    String genres;
    /**
     * The Release date.
     */
    String release_date;
    /**
     * The Type.
     */
    String type;
    /**
     * The Synopsis.
     */
    String synopsis;
    /**
     * The Rating.
     */
    double rating;
    /**
     * The Year.
     */
    int year;
    /**
     * The Active.
     */
    int active;

    /**
     * The Max id.
     */
    int maxID;

    /**
     * Instantiates a new Movie.
     *
     * @param id the id
     */
    public Movie(int id) {
        majAttributsMovie(id, 0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();

            PreparedStatement sql2 = connection.prepareStatement("select max(mov_id) from movie");
            ResultSet rs2 = sql2.executeQuery();
            if (rs2.first()) {
                maxID = rs2.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            //
        }
    }

    /**
     * Maj attributs movie.
     *
     * @param id   the id
     * @param diff the diff
     */
    void majAttributsMovie(int id, int diff) {
        if (id > 0) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = ConnectionSingleton.getConnection();

                PreparedStatement sql2 = connection.prepareStatement("select count(*) from movie where mov_id = ?");
                sql2.setInt(1, id);
                ResultSet rs2 = sql2.executeQuery();
                if (rs2.first()) {
                    int temp = rs2.getInt(1);
                    if (temp == 1) {
                        PreparedStatement sql = connection.prepareStatement("select * from movie where mov_id = ?");
                        sql.setInt(1, id);
                        ResultSet rs = sql.executeQuery();
                        while (rs.next()) {
                            this.mov_id = rs.getInt(1);
                            this.imdb_id = rs.getInt(2);
                            this.title = rs.getString(3);
                            this.poster = rs.getString(4);
                            this.genres = rs.getString(5);
                            this.release_date = rs.getString(6);
                            this.type = rs.getString(7);
                            this.synopsis = rs.getString(8);
                            this.rating = rs.getDouble(9);
                            this.year = rs.getInt(10);
                            this.active = rs.getInt(11);
                        }
                    } else {
                        if (diff == 0) {
                            id = id + 1;
                            majAttributsMovie(id, 0);
                        } else {
                            id = id - 1;
                            majAttributsMovie(id, 1);
                        }
                    }
                }
            } catch (SQLException | ClassNotFoundException throwables) {
                //
            }
        }
    }

    /**
     * Update.
     *
     * @param nvNum the nv num
     * @param diff  the diff
     */
    public void update(int nvNum, int diff) {
        if (nvNum <= this.maxID && nvNum > 0) {
            majAttributsMovie(nvNum, diff);
            super.update();
        } else {
            new ViewError("Veuillez ressaisir un id correct !");
        }
    }

    /**
     * Gets mov id.
     *
     * @return the mov id
     */
    public int getMov_id() {
        return mov_id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets poster.
     *
     * @return the poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Gets genres.
     *
     * @return the genres
     */
    public String getGenres() {
        return genres;
    }

    /**
     * Gets release date.
     *
     * @return the release date
     */
    public String getRelease_date() {
        return release_date;
    }

    /**
     * Gets synopsis.
     *
     * @return the synopsis
     */
    public String getSynopsis() {
        return synopsis;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mov_id=" + mov_id +
                ", imdb_id=" + imdb_id +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", genres='" + genres + '\'' +
                ", release_date='" + release_date + '\'' +
                ", type='" + type + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", rating=" + rating +
                ", year=" + year +
                ", active=" + active +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return mov_id == movie.mov_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mov_id);
    }
}
