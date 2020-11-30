package com.modele;

import com.activerecord.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The type Participants.
 */
public class Participants extends Modele {

    /**
     * The Liste p.
     */
    ArrayList<Participants.Participant> listeP = new ArrayList<>();

    /**
     * Instantiates a new Participants.
     *
     * @param id the id
     */
    public Participants(int id) {
        updateListe(id);
    }

    /**
     * Update liste.
     *
     * @param id the id
     */
    public void updateListe(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement sql = connection.prepareStatement("select peo_name, role.rol_name from people natural join movie natural join role where role.mov_id = ?");
            sql.setInt(1, id);
            ResultSet rs = sql.executeQuery();
            //int n = 0;
            while (rs.next()) {
                // addAButtonGridBagLayout(rs.getString(1) + " : " + rs.getString(2),jPanel,n);
                this.listeP.add(new Participant(rs.getString(1), rs.getString(2)));
                // n++;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update.
     *
     * @param id the id
     */
    public void update(int id) {
        updateListe(id);
        super.update();
    }

    /**
     * Gets liste p.
     *
     * @return the liste p
     */
    public ArrayList<Participant> getListeP() {
        return listeP;
    }

    /**
     * The type Participant.
     */
    public class Participant {

        private final String name;

        private final String role;

        /**
         * Instantiates a new Participant.
         *
         * @param name the name
         * @param role the role
         */
        public Participant(String name, String role) {
            this.name = name;
            this.role = role;
        }


        /**
         * Gets name.
         *
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * Gets role.
         *
         * @return the role
         */
        public String getRole() {
            return role;
        }

    }

}
