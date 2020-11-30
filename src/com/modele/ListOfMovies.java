package com.modele;

import com.activerecord.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;

public class ListOfMovies extends Modele {

    ArrayList<Movie> movies = new ArrayList<>();

    Movie movieA; //2000

    Movie movieB;

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

    public int getFirstElement() {
        this.movieA = this.movies.get((int) (Math.random() * this.movies.size()));
        return movieA.getMov_id();
    }

    private static HashMap<Integer, Double> sortByValues(HashMap<Integer, Double> map) {
        List<Entry<Integer, Double>> list = new LinkedList<>(map.entrySet());
        list.sort(Entry.comparingByValue());
        HashMap<Integer, Double> sortedHashMap = new LinkedHashMap<>();
        for (Entry<Integer, Double> o : list) {
            sortedHashMap.put(o.getKey(), o.getValue());
        }
        return sortedHashMap;
    }

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
        Iterator<Movie> iterator1 = movies.iterator();
        while (iterator1.hasNext()) {
            Movie v = iterator1.next();
            for (Entry<Integer, Double> mv2 : tmp) {
                if (v.getMov_id() == (mv2.getKey())) {
                    iterator1.remove();
                }
            }
        }
    }

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
        Iterator<Movie> iterator1 = movies.iterator();
        while (iterator1.hasNext()) {
            Movie v = iterator1.next();
            for (Entry<Integer, Double> mv2 : tmp) {
                if (v.getMov_id() == (mv2.getKey())) {
                    iterator1.remove();
                }
            }
        }
    }

    /**
     * Idem mais avec des listes de films
     */
    public HashMap<Integer, Double> cosinus_sim_Bis() {
        HashMap<Integer, Double> resultat = new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionSingleton.getConnection();
            ArrayList<Double> hashMap1 = new ArrayList<>();

            PreparedStatement sql1 = connection.prepareStatement("select * from tag_relevance where mov_id = ?");
            sql1.setInt(1, this.getMovieA().getMov_id()); //premier de la liste sera la reference
            ResultSet rs1 = sql1.executeQuery();
            while (rs1.next()) {
                hashMap1.add(rs1.getDouble(3));
            }
            for (int i = 0; i < movies.size(); i++) {
                Movie m = movies.get(i);
                if (m.getMov_id() != this.movieA.getMov_id()) {
                    ArrayList<Double> hashMap2 = new ArrayList<>();
                    PreparedStatement sql2 = connection.prepareStatement("select * from tag_relevance where mov_id = ?");
                    sql2.setInt(1, movies.get(i).getMov_id());
                    ResultSet rs2 = sql2.executeQuery();
                    while (rs2.next()) {
                        hashMap2.add(rs2.getDouble(3));
                    }
                    double numerateur = 0;
                    double racineLeft = 0;
                    double racineRight = 0;
                    if (hashMap1.size() == 0 || hashMap2.size() == 0) {
                        resultat.put(movies.get(i).getMov_id(), (double) 0);
                    } else if (hashMap1.size() != hashMap2.size()) {
                        int sizeMin = hashMap1.size();
                        if (sizeMin > hashMap2.size())
                            sizeMin = hashMap2.size();
                        for (int j = 0; j < sizeMin; j++) {
                            numerateur = numerateur + hashMap1.get(j) * hashMap2.get(j);
                            racineLeft = racineLeft + (hashMap1.get(j) * hashMap1.get(j));
                            racineRight = racineRight + (hashMap2.get(j) * hashMap2.get(j));
                        }
                        double denominateur = Math.sqrt(racineLeft) * Math.sqrt(racineRight);
                        resultat.put(movies.get(i).getMov_id(), numerateur / denominateur);
                    } else {
                        for (int j = 0; j < hashMap1.size(); j++) {
                            numerateur = numerateur + hashMap1.get(j) * hashMap2.get(j);
                            racineLeft = racineLeft + (hashMap1.get(j) * hashMap1.get(j));
                            racineRight = racineRight + (hashMap2.get(j) * hashMap2.get(j));
                        }
                        double denominateur = Math.sqrt(racineLeft) * Math.sqrt(racineRight);
                        resultat.put(movies.get(i).getMov_id(), numerateur / denominateur);
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return resultat;
    }

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

    public Movie getMovieA() {
        return movieA;
    }

    public Movie getMovieB() {
        return movieB;
    }
}
