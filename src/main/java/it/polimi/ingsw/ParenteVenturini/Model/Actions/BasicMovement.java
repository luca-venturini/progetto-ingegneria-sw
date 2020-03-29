package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public class BasicMovement implements Action {

    @Override
    public void doAction(Point point, Board board, Worker worker) {
        if( isValid(point, board, worker) ){
            worker.setPosition(point);
        }
    }

    @Override
    public boolean isValid(Point point, Board board, Worker worker) {
        return getPossibleMovement(board, worker).contains(point);
        /*
        if( board.isOccupied(point) || board.isThereDoom(point) )
            return false;
        if(board.blockLevel(point) - board.blockLevel(worker.getPosition()) > 1)
            return false;
        if(point.getX() > 4 || point.getY() > 4 || point.getX() < 0 || point.getY() < 0)
            return false;
        */
    }


    public List<Point> getPossibleMovement(Board board, Worker worker){
        Point workerPoint = new Point(worker.getPosition());
        List<Point> possibleMovements = new ArrayList<>();
        /*
        Map:
        (x, y)

        (0,0) (0,1) (0,2) (0,3) (0,4)
        (1,0) (1,1) (1,2) (1,3) (1,4)
        (2,0) (2,1) (2,2) (2,3) (2,4)
        (3,0) (3,1) (3,2) (3,3) (3,4)
        (4,0) (4,1) (4,2) (4,3) (4,4)

         */
        for(int i=workerPoint.getX()-1; i<workerPoint.getX()+1; i++){
            for(int j=workerPoint.getY()-1;j<workerPoint.getY()+1;j++){
                if(!(i<0 || i>4 || j<0 || j>4)){
                    if(! ( board.isOccupied(i, j) || board.isThereDoom(i, j) ) ){
                        if(board.blockLevel(i, j) - board.blockLevel(worker.getPosition()) <= 1)
                            possibleMovements.add(new Point(i, j));
                    }
                }
            }
        }
        return possibleMovements;
    }

}
