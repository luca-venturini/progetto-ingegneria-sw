package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;

import java.io.Serializable;

public class Worker implements Serializable {

    private Point position;
    private Point lastPosition;
    private Player player;
    private int colour;

    public Worker(Point position, Player player, int colour) {
        this.position = position;
        this.lastPosition = position;
        this.player = player;
        this.colour = colour;
    }

    public Player getPlayer() {
        return player;
    }

    public int getColour() {
        return colour;
    }

    public Point getPosition(){
        return position;
    }

    public OpponentEffect getEffect(){
        return player.getOpponentEffectPlayer();
    }

    public void setPosition(Point point){
        setLastPosition(this.position);
        this.position = point;
    }

    public Player getPlayer() {
        return player;
    }

    public Point getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Point lastPosition) {
        this.lastPosition = lastPosition;
    }
}
