package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;

import java.io.Serializable;

public class Worker implements Serializable {

    private Point position;
    private Point lastPosition;
    private Player player;
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
     * @param point return the position where the worker is
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
