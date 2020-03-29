package it.polimi.ingsw.ParenteVenturini.Model;

public class Point{

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setPoint(Point newPoint){
        this.x = newPoint.x;
        this.y = newPoint.y;
    }

    public boolean equals(Point p){
        return (this.x == p.getX() && this.y == p.getY());
    }
}
