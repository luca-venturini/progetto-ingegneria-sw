package it.polimi.ingsw.ParenteVenturini.Model;

import java.io.Serializable;

/**
 * class that represent a point of the board
 */
public class Point implements Serializable {

    private int x;
    private int y;

    /**
     * create a new point
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * create a copy of a point
     * @param p point
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * get the X coordinate
     * @return the x coordinate of the point
     */
    public int getX() {
        return x;
    }

    /**
     * get the Y coordinate
     * @return the y coordinate of the point
     */
    public int getY() {
        return y;
    }

    /**
     * set a new point
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setPoint(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * set a new point
     * @param newPoint a copy of the point
     */

    public void setPoint(Point newPoint){
        this.x = newPoint.x;
        this.y = newPoint.y;
    }

    /**
     * compare two points
     * @param p the other point
     * @return true if the points are equal
     */
    public boolean equals(Point p){
        return (this.x == p.getX() && this.y == p.getY());
    }

    /**
     * compare two points
     * @param x x coordinate of the other point
     * @param y y coordinate of the other point
     * @return true if the points are equal
     */

    public boolean equals(int x, int y){
        return (this.x == x && this.y == y );
    }

    /**
     * string representation of a point
     * @return string representation of a point
     */
    public String toString(){
        return "("+x+", "+y+")";
    }
}
