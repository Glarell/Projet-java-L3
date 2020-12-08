package com.modele;

import com.activerecord.ConnectionSingleton;
import com.view.ViewError;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Personnage.
 */
public class Personnage extends Modele {

    /**
     * The Peo id.
     */
    int peo_id;

    /**
     * The Peo name.
     */
    String peo_name;

    /**
     * The Films.
     */
    public ArrayList<ArrayList<String>> films = new ArrayList<>();

    /**
     * Instantiates a new Personnage.
     *
     * @param peo_id the peo id
     */
    public Personnage(int peo_id) {
        majAttribut(peo_id);
    }

    /**
     * Maj attribut.
     *
     * @param id the id
     */
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
            PreparedStatement sql2 = connection.prepareStatement("select distinct poster,title,release_date,rol_name, mov_id from movie natural join people natural join role where peo_name like ?");
            sql2.setString(1, this.peo_name);
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
            if (films.isEmpty())
                throw new NumberFormatException();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        } catch (NumberFormatException e) {
            new ViewError("Erreur lors du calcul des films du participant !");
        }
    }

    /**
     * Gets people by name.
     *
     * @param peo_name the peo name
     * @return the people by name
     * @throws SQLException           the sql exception
     * @throws ClassNotFoundException the class not found exception
     */
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

    /**
     * Update.
     *
     * @param peo_id the peo id
     */
    public void update(int peo_id) {
        majAttribut(peo_id);
        super.update();
    }

    /**
     * Gets peo id.
     *
     * @return the peo id
     */
    public int getPeo_id() {
        return peo_id;
    }

    /**
     * Sets peo id.
     *
     * @param peo_id the peo id
     */
    public void setPeo_id(int peo_id) {
        this.peo_id = peo_id;
    }

    /**
     * Gets peo name.
     *
     * @return the peo name
     */
    public String getPeo_name() {
        return peo_name;
    }

    /**
     * Sets peo name.
     *
     * @param peo_name the peo name
     */
    public void setPeo_name(String peo_name) {
        this.peo_name = peo_name;
    }
}
