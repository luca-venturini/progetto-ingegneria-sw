package it.polimi.ingsw.ParenteVenturini.Model;

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

    public void updateLevel(int level){

        this.level = level;
    }

    public void setDoom(boolean doom) {

        this.doom = doom;
    }

}
