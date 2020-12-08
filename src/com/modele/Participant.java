package com.modele;

/**
 * The type Participant.
 */
public class Participant extends Modele {

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

    @Override
    public void update() {
        super.update();
    }
}
