package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalMovementException;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Common walk action
 */
public class BasicMovement extends Action {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction(Point point, Board board, Worker worker) throws IllegalMovementException {
        if( isValid(point, board, worker) ){
            worker.setPosition(point);
        }
        else throw new IllegalMovementException();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isValid(Point point, Board board, Worker worker) {
        List<Point> possibleActions=getPossibleActions(board,worker);
        return checkValid(point,possibleActions);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> getPossibleActions(Board board, Worker worker) {
        List<Point> possibleActions=super.getPossibleActions(board, worker);
        List<Point> checkedActions= new ArrayList<>();
        for(Point p: possibleActions){
            if( !( board.isOccupied(p) || board.isThereDome(p) ) && (board.blockLevel(p) - board.blockLevel(worker.getPosition()) <= 1) ){
                checkedActions.add(p);
            }
        }
        return checkedActions;
    }
}
