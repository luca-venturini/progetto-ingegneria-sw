package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;

import java.io.Serializable;

/**
 * This class represent one of the twenty-five points of the board
 */
public class Block implements Serializable {

    /** level is the number of constructions built on the block */
    private int level;

    /** dome return true if there is a dome on this Block */
    private boolean dome;

    /**
     * set initial values
     */
    public Block() {
        this.level = 0;
        this.dome = false;
    }

    /**
     *
     * @return the block level
     */
    public int getLevel() {
        return level;
    }

    /**
     *
     * @return true if there is a dome
     */
    public boolean isDome() {
        return dome;
    }

    /**
     *
     * @param level the new level of this block
     * @throws IllegalBlockUpdateException thrown if level values is incorrect
     */

    public void updateLevel(int level) throws IllegalBlockUpdateException {
        if(level>4 || level<0) {
            throw new IllegalBlockUpdateException();
        }
        this.level = level;
        if(level==4)
            this.setDome(true);
    }

    /**
     * set the dome on the block
     * @param dome true if you ant to build a dome
     */

    public void setDome(boolean dome) {
        this.dome = dome;
    }

}
