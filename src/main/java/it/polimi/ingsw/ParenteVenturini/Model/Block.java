package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;

public class Block {

    private int level;
    private boolean doom;

    public Block() {
        this.level = 0;
        this.doom = false;
    }

    public int getLevel() {
        return level;
    }

    public boolean isDoom() {
        return doom;
    }

    public void updateLevel(int level) throws IllegalBlockUpdateException {
        if(level>4) {
            throw new IllegalBlockUpdateException();
        }
        this.level = level;
        if(level==4)
            this.setDoom(true);
    }

    public void setDoom(boolean doom) {

        this.doom = doom;
    }

}
