package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBuildingException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalMovementException;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class MinotaurMovement extends Action {
    @Override
    public void doAction(Point point, Board board, Worker worker) throws IllegalMovementException {
        if( isValid(point, board, worker) ){
            if(board.isOccupied(point)){
                Worker opponent = board.findByPosition(point);
                Point thirdPoint = new Point(2*point.getX()-worker.getPosition().getX(), 2*point.getY()-worker.getPosition().getY());
                opponent.setPosition(thirdPoint);
            }
            worker.setPosition(point);
        }
        else throw new IllegalMovementException();
    }

    @Override
    public boolean isValid(Point point, Board board, Worker worker) {
        return super.isValid(point, board, worker);
    }

    @Override
    public List<Point> getPossibleActions(Board board, Worker worker) {
        List<Point> possibleActions =  super.getPossibleActions(board, worker);
        for (Point p: possibleActions){
            if( board.isThereDoom(p) ){
                possibleActions.remove(p);
            }
            if(board.isOccupied(p)){
                Point thirdPoint = new Point(2*p.getX()-worker.getPosition().getX(), 2*p.getY()-worker.getPosition().getY());
                if( !board.isValidPoint(thirdPoint) || board.isOccupied(thirdPoint)){
                    possibleActions.remove(p);
                }
            }
        }
        return possibleActions;
    }
}
