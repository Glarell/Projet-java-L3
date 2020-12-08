package com.modele;

import java.util.Observable;

/**
 * The type Modele.
 */
public abstract class Modele extends Observable {

    /**
     * Update.
     */
    public void update() {
        setChanged();
        notifyObservers();
    }

}
