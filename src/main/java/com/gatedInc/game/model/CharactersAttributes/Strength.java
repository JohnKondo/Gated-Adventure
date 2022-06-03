package com.gatedInc.game.model.CharactersAttributes;

public class Strength {

    private int currentStrength;

    public Strength() {
        this.currentStrength = 1;
    }

    public Strength(int strength) {
        this.currentStrength = strength;
    }

    public void getStronger() {
        currentStrength += 1;
    }

    public int getCurrentStrength() {
        return currentStrength;
    }
}
