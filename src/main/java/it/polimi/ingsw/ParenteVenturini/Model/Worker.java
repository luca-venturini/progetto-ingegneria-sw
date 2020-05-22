package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;

import java.io.Serializable;

/**
 * class that represent a worker
 */
public class Worker implements Serializable {

    /** the worker position */
    private Point position;
    /** the previous worker position */
    private Point lastPosition;
    /** the player that owns the worker */
    private Player player;
    /** the worker color */
    private int colour;

    /**
     * init the worker class
     * @param position the position of the worker
     * @param player the owner of the worker
     * @param colour the worker's color
     */
    public Worker(Point position, Player player, int colour) {
        this.position = position;
        this.lastPosition = position;
        this.player = player;
        this.colour = colour;
    }

    /**
     * get the color of the worker
     * @return the worker's color
     */
    public int getColour() {
        return colour;
    }

    /**
     * get the worker's position
     * @return the workser's position
     */
    public Point getPosition(){
        return position;
    }

    /**
     * get the worker's / player effect
     * @return the effect of the worker
     */
    public OpponentEffect getEffect(){
        return player.getOpponentEffectPlayer();
    }

    /**
     * get the position of the worker
     * @param point is the position in which the worker move
     */

    public void setPosition(Point point){
        setLastPosition(this.position);
        this.position = point;
    }

    /**
     * return the owner of the worker
     * @return reference to player that own the worker
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * get the last position of the worker
     * @return the point where the was before
     */

    public Point getLastPosition() {
        return lastPosition;
    }

    /**
     * update the worker's last position
     * @param lastPosition the point were the worker was before
     */
    public void setLastPosition(Point lastPosition) {
        this.lastPosition = lastPosition;
    }
}
