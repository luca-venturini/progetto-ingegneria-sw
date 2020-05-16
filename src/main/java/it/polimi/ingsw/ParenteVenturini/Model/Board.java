package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * The game board
 */
public class Board {

    /** multidimensional array which represent teh board game */
    private Block[][] board;
    /** list of workers placed in this board */
    private List<Worker> workers;

    /**
     * init the board
     */
    public Board() {
        board = new Block[5][5];
        workers = new ArrayList<Worker>();

        for(int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                board[i][j] = new Block();
            }
        }
    }

    /**
     * add a new worker on the board
     * @param w the worker you want to add to the board
     */

    public void setWorker(Worker w) {
        workers.add(w);
    }

    /**
     * check if a specific point on the board is occupied
     *
     * @param point the point you want to check
     * @return true if the point is occupied
     */

    public boolean isOccupied(Point point) {
        for (Worker w : workers) {
            if (w.getPosition().equals(point))
                return true;
        }
        return false;
    }

    /**
     *
     * check if a specific point on the board is occupied
     *
     * @param x the x coordinate of the point you want to check
     * @param y the y coordinate of the point you want to check
     * @return true if the point is occupied
     */

    public boolean isOccupied(int x, int y) {
        Point pointTemp = new Point(x, y);
        for (Worker w : workers) {
            if (w.getPosition().equals(pointTemp))
                return true;
            }
        return false;
    }

    /**
     * check if there is a dome on a specific point
     *
     * @param point the point you want to check
     * @return true if there i a dome
     */

    public boolean isThereDome(Point point) {
        return board[point.getX()][point.getY()].isDome();
    }

    /**
     *
     * check if there is a dome on a specific point
     * @param x the x coordinate of the point you want to check
     * @param y the y coordinate of the point you want to check
     * @return true if there is a dome
     */

    public boolean isThereDome(int x, int y) {
        return board[x][y].isDome();
    }

    /**
     * get the block level
     * @param point the point you want ot check
     * @return the block level
     */
    public int blockLevel(Point point) {
        return board[point.getX()][point.getY()].getLevel();
    }

    /**
     *
     * get the block level
     * @param x the x coordinate of the point you want to check
     * @param y the y coordinate of the point you want to check
     * @return the block level
     */

    public int blockLevel(int x, int y) {
            return board[x][y].getLevel();
    }

    /**
     * set the block level
     *
     * @param point the point you want ot set
     * @param level the level
     * @throws IllegalBlockUpdateException thrown if you can't update the block
     */

    public void setBlockLevel(Point point, int level) throws IllegalBlockUpdateException {
            board[point.getX()][point.getY()].updateLevel(level);
    }

    /**
     *
     * set dome
     * @param point the point you want ot set
     * @param x true if you want to set the dome
     */
    public void setDome(Point point,boolean x) {
            board[point.getX()][point.getY()].setDome(x);
    }


    /**
     * find the worker by his position
     * @param point the point of the board you are referring to
     * @return the worker placed in that specific point if there's one, else null
     */
    public Worker findByPosition(Point point) {
        for (Worker w : workers) {
            if (w.getPosition().equals(point))
                return w;
            }
            return null;
    }

    /**
     * find the worker by his position
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return the worker placed in that specific point if there's one, else null
     */

    public Worker findByPosition(int x, int y) {
        Point point= new Point(x,y);
        for (Worker w : workers) {
            if (w.getPosition().equals(point))
                return w;
        }
        return null;
    }

    /**
     * check if th epoint is valid
     * @param point point of the board
     * @return true if the coordinates of the point are in the board
     */
    public boolean isValidPoint(Point point){
        return point.getX() >= 0 && point.getX() <= 4 && point.getY() >= 0 && point.getY() <= 4;
    }

    /**
     *
     * check if the point is valid
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return true if the coordinates of the point are in the board
     */
    public boolean isValidPoint(int x, int y){
        return x >= 0 && x <= 4 && y >= 0 && y <= 4;
    }

    /**
     * check if the point is a perimeter point
     * @param point point of the board
     * @return true if the point is on the perimeter
     */
    public boolean isPerimeterPoint(Point point){
        return point.getX() == 0 || point.getX() == 4 || point.getY() == 0 || point.getY() == 4;
    }

    /**
     * find a block by his position
     * @param x x coordinate of the point
     * @param y y coordinate of the point
     * @return the block placed at that point
     */
    public Block getBlock(int x, int y){
        return board[x][y];
    }

    /**
     * get the list of all of the workers placed on the board
     * @return list of all of the workers
     */
    public List<Worker> getWorkers() {
        return workers;
    }

    /**
     * get the complete list of all of the blocks on the board
     * @return list of all of the blocks in the board
     */
    public List<Block> getBlocks(){
        List<Block> blocks = new ArrayList<>();
        for(int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                blocks.add(board[i][j]);
            }
        }
        return blocks;
    }
}
