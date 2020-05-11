package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBuildingException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalMovementException;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public abstract class Action {

    /**
     * do the action
     * @param point the selected point
     * @param board the used board
     * @param worker the worker
     * @throws IllegalBuildingException thrown if you can't build
     * @throws IllegalMovementException thrown if yu can't move
     */
    public abstract void  doAction(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException;

    /**
     * evaluate the point before the action
     * @param point the selected point
     * @param board the used board
     * @param worker the worker
     * @return true if the move is valid
     */
    public abstract boolean  isValid(Point point, Board board, Worker worker);

    /**
     *
     * @param point a point
     * @param possibleActions list of points
     * @return true if the point is in the list
     */
    protected boolean  checkValid(Point point,List<Point> possibleActions) {
        for(Point p: possibleActions){
            if(p.equals(point))
                return true;
        }
        return false;
    }

    /**
     * list of possible points where you can do the action
     * @param board the board
     * @param worker the worker
     * @return list of possibile points
     */

    public  List<Point> getPossibleActions(Board board, Worker worker) {
        Point workerPoint = new Point(worker.getPosition());
        List<Point> possibleActions = new ArrayList<>();
        /*
        Map:
        (x, y)

        (0,0) (0,1) (0,2) (0,3) (0,4)
        (1,0) (1,1) (1,2) (1,3) (1,4)
        (2,0) (2,1) (2,2) (2,3) (2,4)
        (3,0) (3,1) (3,2) (3,3) (3,4)
        (4,0) (4,1) (4,2) (4,3) (4,4)

         */
        for(int i=workerPoint.getX()-1; i<=workerPoint.getX()+1; i++){
            for(int j=workerPoint.getY()-1;j<=workerPoint.getY()+1;j++){
                if(!(i<0 || i>4 || j<0 || j>4)){
                    possibleActions.add(new Point(i, j));
                }
            }
        }
        return possibleActions;
    }

}
