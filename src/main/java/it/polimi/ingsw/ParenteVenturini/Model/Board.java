package it.polimi.ingsw.ParenteVenturini.Model;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Block board[][];
    private List<Worker> workers;

    public Board() {
        board = new Block[5][5];
        workers = new ArrayList<Worker>();

        for(int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                board[i][j] = new Block();
            }
        }
    }

    public void setWorker(Worker w) {
        workers.add(w);
    }

    public boolean isOccupied(Point point){
        for(Worker w: workers){
            if(w.getPosition().equals(point))
                return true;
        }
        return false;
    }

    public boolean isOccupied(int x, int y){
        Point pointTemp = new Point(x, y);
        for(Worker w: workers){
            if(w.getPosition().equals(pointTemp))
                return true;
        }
        return false;
    }

    public boolean isThereDoom(Point point){
        return board[point.getX()][point.getY()].isDoom();
    }

    public boolean isThereDoom(int x, int y){
        return board[x][y].isDoom();
    }

    public int blockLevel(Point point){
        return board[point.getX()][point.getY()].getLevel();
    }

    public int blockLevel(int x, int y){
        return board[x][y].getLevel();
    }



}
