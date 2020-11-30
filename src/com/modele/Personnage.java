package com.modele;

import com.activerecord.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Personnage extends Modele {

    int peo_id;

    String peo_name;

    public ArrayList<ArrayList<String>> films = new ArrayList<>();

    public Personnage(int peo_id) {
        majAttribut(peo_id);
    }

    void majAttribut(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement sql = connection.prepareStatement("select * from people where peo_id = ?");
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                this.peo_id = rs.getInt(1);
                this.peo_name = rs.getString(2);
            }
            // liste des films
            PreparedStatement sql2 = connection.prepareStatement("select distinct poster,title,release_date,rol_name, mov_id from movie natural join people natural join role where role.mov_id = ?");
            sql2.setInt(1, id);
            ResultSet rs2 = sql2.executeQuery();
            while (rs2.next()) {
                ArrayList<String> film = new ArrayList<>();
                film.add(rs2.getString(1));
                film.add(rs2.getString(2));
                film.add(rs2.getString(3));
                film.add(rs2.getString(4));
                film.add(rs2.getString(5));
                this.films.add(film);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public static int getPeopleByName(String peo_name) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = ConnectionSingleton.getConnection();
        PreparedStatement sql = connection.prepareStatement("select peo_id from people where peo_name = ?");
        sql.setString(1, peo_name);
        ResultSet rs = sql.executeQuery();
        int id = 1; //ToyStory si pb
        while (rs.next()) {
            id = rs.getInt(1);
        }
        return id;
    }

    public void update(int peo_id) {
        majAttribut(peo_id);
        super.update();
    }

    public int getPeo_id() {
        return peo_id;
    }

    public void setPeo_id(int peo_id) {
        this.peo_id = peo_id;
    }

    public String getPeo_name() {
        return peo_name;
    }

    public void setPeo_name(String peo_name) {
        this.peo_name = peo_name;
    }
}
