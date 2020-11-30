package com.modele;

import com.activerecord.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Movie extends Modele {

    int mov_id;
    int imdb_id;
    String title;
    String poster;
    String genres;
    String release_date;
    String type;
    String synopsis;
    double rating;
    int year;
    int active;

    int maxID;

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

    public void update(int nvNum, int diff) {
        if (nvNum <= this.maxID) {
            majAttributsMovie(nvNum, diff);
            super.update();
        }
    }

    public int getMov_id() {
        return mov_id;
    }

    public void setMov_id(int mov_id) {
        this.mov_id = mov_id;
    }

    public int getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(int imdb_id) {
        this.imdb_id = imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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
}
