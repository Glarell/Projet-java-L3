package com.modele;

import com.activerecord.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Cette classe correspond au traitement de la liste de tous les films avec algorithme
 */
public class ListOfMovies extends Modele {

    /**
     * The Movies.
     */
    ArrayList<Movie> movies = new ArrayList<>(); // Liste complete des films

    /**
     * The Movie a.
     */
    Movie movieA; // Film de gauche

    /**
     * The Movie b.
     */
    Movie movieB; // Film de droite

    /**
     * Instancie la liste de Films
     */
    public ListOfMovies() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            PreparedStatement sql2 = connection.prepareStatement("select mov_id from movie");
            ResultSet rs2 = sql2.executeQuery();
            while (rs2.next()) {
                int id = rs2.getInt(1);
                this.movies.add(new Movie(id));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Gets first element.
     * Permet d'avoir l'element A = film gauche
     * @return the first element
     */
    public int getFirstElement() {
        this.movieA = this.movies.get((int) (Math.random() * this.movies.size()));
        return movieA.getMov_id();
    }

    /**
     * Permet de trier la hashmap par similurarité (ordre croissant)
     * @param map HashMap non trié
     * @return HashMap trié par valeur ordre croissant
     */
    private static HashMap<Integer, Double> sortByValues(HashMap<Integer, Double> map) {
        List<Entry<Integer, Double>> list = new LinkedList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        HashMap<Integer, Double> sortedHashMap = new LinkedHashMap<>();
        for (Entry<Integer, Double> o : list) {
            sortedHashMap.put(o.getKey(), o.getValue());
        }
        return sortedHashMap;
    }

    /**
     * Supprime les éléments les moins proches de A
     */
    public void divideByBetter() {
        List<Entry<Integer, Double>> tmp;
        int size = this.movies.size();
        HashMap<Integer, Double> hashMap = sortByValues(cosinus_sim_Bis());
        if (size % 2 == 0) {
            LinkedList<Entry<Integer, Double>> list = new LinkedList<>(hashMap.entrySet());
            tmp = list.subList(0, size / 2);
        } else {
            LinkedList<Entry<Integer, Double>> list = new LinkedList<>(hashMap.entrySet());
            tmp = list.subList((int) ((double) (size / 2) - 0.5), size - 1);
        }
        ArrayList<Movie> tmpMovies = new ArrayList<>();
        for (Entry<Integer, Double> entry : tmp) {
            tmpMovies.add(new Movie(entry.getKey()));
        }
        this.movies.removeAll(tmpMovies); // suppression par liste
    }

    /**
     * Supprime les éléments les moins proches de B
     */
    public void divideByWorst() {
        List<Entry<Integer, Double>> tmp;
        int size = this.movies.size();
        HashMap<Integer, Double> hashMap = sortByValues(cosinus_sim_Bis());
        if (size % 2 == 0) {
            LinkedList<Entry<Integer, Double>> list = new LinkedList<>(hashMap.entrySet());
            tmp = list.subList(0, size / 2);
        } else {
            LinkedList<Entry<Integer, Double>> list = new LinkedList<>(hashMap.entrySet());
            tmp = list.subList((int) ((double) (size / 2) + 0.5), size - 1);
        }
        ArrayList<Movie> tmpMovies = new ArrayList<>();
        for (Entry<Integer, Double> entry : tmp) {
            tmpMovies.add(new Movie(entry.getKey()));
        }
        this.movies.removeAll(tmpMovies); // suppression par liste
    }

    /**
     * Algorithme sur la similarité
     *
     * @return the hash map, HashMap<id_mov, taux de simularité par rapport a A>
     */
    public HashMap<Integer, Double> cosinus_sim_Bis() {
        HashMap<Integer, Double> resultat = new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            HashMap<Integer, Double> hashMap1 = new HashMap<>();

            PreparedStatement sql1 = connection.prepareStatement("select * from tag_relevance where mov_id = ?");
            sql1.setInt(1, this.getMovieA().getMov_id()); //premier de la liste sera la reference = film de droite (A)
            ResultSet rs1 = sql1.executeQuery();
            while (rs1.next()) {
                hashMap1.put(rs1.getInt(2), rs1.getDouble(3));
            }
            for (Movie m : movies) { // on parcours tous les films pour calculer leur simularité avec le film A
                if (m.getMov_id() != this.movieA.getMov_id()) {
                    HashMap<Integer, Double> hashMap2 = new HashMap<>();
                    PreparedStatement sql2 = connection.prepareStatement("select * from tag_relevance where mov_id = ?");
                    sql2.setInt(1, m.getMov_id());
                    ResultSet rs2 = sql2.executeQuery();
                    while (rs2.next()) {
                        hashMap2.put(rs2.getInt(2), rs2.getDouble(3));
                    }
                    if (hashMap1.size() == 0 || hashMap2.size() == 0) { // si une des deux listes est égale à 0 on attribue 0 comme simularité
                        resultat.put(m.getMov_id(), (double) 0);
                    } else { // sinon on calcule la similarité entre les memes tags
                        double numerateur = 0;
                        double racineLeft = 0;
                        double racineRight = 0;
                        for (Entry<Integer, Double> entry : hashMap1.entrySet()) {
                            Double d = hashMap2.get(entry.getKey());
                            if (d != null) {
                                numerateur = numerateur + (entry.getValue() * d);
                                racineLeft = racineLeft + (entry.getValue() * entry.getValue());
                                racineRight = racineRight + (d * d);
                            }
                        }
                        double denominateur = Math.sqrt(racineLeft) * Math.sqrt(racineRight);
                        resultat.put(m.getMov_id(), numerateur / denominateur);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultat;
    }

    /**
     * permet d'avoir l'id du film ayant le moins de simularité avec le film A
     *
     * @return the int of the movie B
     */
    public int leastSimular() {
        int idWeak = minOfHashMap(cosinus_sim_Bis());
        this.movieB = new Movie(idWeak);
        if (this.movieB.getMov_id() == (this.movieA.getMov_id()) && this.movies.size() == 2) {
            this.movieB = this.movies.get(0);
            idWeak = this.movies.get(0).getMov_id();
            if (this.movieB.getMov_id() == (this.movieA.getMov_id())) {
                this.movieB = this.movies.get(1);
                idWeak = this.movies.get(1).getMov_id();
            }
        }
        return idWeak;
    }

    /**
     * Min of hash map integer.
     *
     * @param hashMap the hash map
     * @return the integer
     */
    public Integer minOfHashMap(HashMap<Integer, Double> hashMap) {
        Set<Entry<Integer, Double>> iterator = hashMap.entrySet();
        double minD = 1;
        int id = this.movieA.getMov_id();
        for (Entry<Integer, Double> entry : iterator) {
            if (entry.getValue() < minD && this.movieA.getMov_id() != entry.getKey()) {
                minD = entry.getValue();
                id = entry.getKey();
            }
        }
        return id;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.movies.size();
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public String toString() {
        return "ListOfMovies{" +
                "movies=" + movies +
                '}';
    }

    /**
     * Gets movie a.
     *
     * @return the movie a
     */
    public Movie getMovieA() {
        return movieA;
    }

    /**
     * Gets movie b.
     *
     * @return the movie b
     */
    public Movie getMovieB() {
        return movieB;
    }
}
