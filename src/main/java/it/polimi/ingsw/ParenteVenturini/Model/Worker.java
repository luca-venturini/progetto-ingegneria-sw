package it.polimi.ingsw.ParenteVenturini.Model;

public class Worker {

    private Point position;
    private Point lastPosition;
    private Player player;

    public Worker(Point position, Player player) {
        this.position = position;
        this.lastPosition = null;
        this.player = player;
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

    /** implementare metodo callMove() **/
}
