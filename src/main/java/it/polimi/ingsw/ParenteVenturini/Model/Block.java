package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;

import java.io.Serializable;

public class Block implements Serializable {

    private int level;
    private boolean dome;

    public Block() {
        this.level = 0;
        this.dome = false;
    }

    public int getLevel() {
        return level;
    }

    public boolean isDome() {
        return dome;
    }

    public void updateLevel(int level) throws IllegalBlockUpdateException {
        if(level>4 || level<0) {
            throw new IllegalBlockUpdateException();
        }
        this.level = level;
        if(level==4)
            this.setDome(true);
    }

    public void setDome(boolean dome) {

        this.dome = dome;
    }

}
