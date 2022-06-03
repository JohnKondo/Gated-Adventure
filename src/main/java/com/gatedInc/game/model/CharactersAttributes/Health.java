package com.gatedInc.game.model.CharactersAttributes;

public class Health {

    private final int MAX_HEALTH;
    private int currentHealth;

    public Health() {
        this.MAX_HEALTH = 6;
        currentHealth = MAX_HEALTH;
    }

    public Health(int MAX_HEALTH) {
        this.MAX_HEALTH = MAX_HEALTH;
        currentHealth = MAX_HEALTH;
    }

    public void heal() {
        if (currentHealth >= 4)
            currentHealth = MAX_HEALTH;
        else
            currentHealth += 2;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void hit(int damage) {
        currentHealth -= damage;
    }
}
