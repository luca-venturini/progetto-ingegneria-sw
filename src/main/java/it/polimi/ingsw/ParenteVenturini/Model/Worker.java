package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Player.Player;

public class Worker {

    private Point position;
    private Point lastPosition;
    private Player player;
    private int colour;

    public Worker(Point position, Player player, int colour) {
        this.position = position;
        this.lastPosition = null;
        this.player = player;
        this.colour= colour;
    }

    public Point getPosition(){
        return position;
    }

    /* win is now in Player */

    public boolean gameOver(Board board){
        return false;
    }

    public void setPosition(Point point){
        setLastPosition(this.position);
        this.position = point;
    }

    public Point getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Point lastPosition) {
        this.lastPosition = lastPosition;
    }
}
