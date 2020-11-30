package com.modele;

import java.util.Observable;

public abstract class Modele extends Observable {

    public void update() {
        setChanged();
        notifyObservers();
    }

}
