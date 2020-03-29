package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalMovementException;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class BasicMovement extends Action {

    @Override
    public void doAction(Point point, Board board, Worker worker) throws IllegalMovementException {
        if( isValid(point, board, worker) ){
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
        List<Point> possibleActions=super.getPossibleActions(board, worker);
        for(Point p: possibleActions){
            if( board.isOccupied(p) || board.isThereDoom(p) ){
                    possibleActions.remove(p);
            }
            if(board.blockLevel(p) - board.blockLevel(worker.getPosition()) <= 1){
                possibleActions.remove(p);
            }
        }
        return possibleActions;
    }
}
