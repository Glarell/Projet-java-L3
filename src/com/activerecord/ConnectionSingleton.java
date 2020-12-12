package com.activerecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe permettant une connexion unique avec la base de données
 */
public class ConnectionSingleton {
    /**
     * Connexion
     */
    private static Connection c;
    /**
     * Instance de la classe pour unifier la connexion
     */
    private static ConnectionSingleton s;
    /**
     * Nom de la base de données
     */
    private String database;

    /**
     * Constructeur privé de la classe qui initialise la connexion avec la base de données
     */
    public ConnectionSingleton() {
        try {
            this.database = "movielens";
            String url = "jdbc:mysql://127.0.0.1:3306/" + this.database;
            c = DriverManager.getConnection(url, "root", "");
            System.out.println("Connection reussi");
        } catch (SQLException e) {
            System.out.println("Connection non reusssi");
            System.out.println(e + "\nVérifiez si l'url est bonne ex: jdbc:mysql://127.0.0.1:3306/movielens ou si la library mysql est bien importé");
        }
    }

    /**
     * Permet de recuperer ou de creer l'unique instance de la classe
     *
     * @return instance
     */
    public static ConnectionSingleton getInstance() {
        if (c == null) {
            s = new ConnectionSingleton();
        } else {
            try {
                if (c.isClosed()) {
                    s = new ConnectionSingleton();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    /**
     * Permet de recuperer ou de creer l'unique connexion
     *
     * @return connection
     * @throws SQLException the sql exception
     */
    public synchronized static Connection getConnection() throws SQLException {
        if (c == null) {
            new ConnectionSingleton();
        }
        return c;
    }
}
