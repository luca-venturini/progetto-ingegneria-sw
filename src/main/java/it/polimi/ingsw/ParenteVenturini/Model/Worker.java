package it.polimi.ingsw.ParenteVenturini.Model;

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

    public boolean win(Board board){
        return false;
    }

    public boolean gameOver(Board board){
        return false;
    }

    public void setPosition(Point point){
        this.position = point;
    }

    /* implementare metodo callMove() */
}
