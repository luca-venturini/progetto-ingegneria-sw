package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBuildingException;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Special build action of Atlas card
 */
public class AtlasConstruction extends Action {

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAction(Point point, Board board, Worker worker) throws IllegalBuildingException {
        if(isValid(point, board, worker)){
            board.setDome(point,true);
        }
        else throw new IllegalBuildingException();
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
        List<Point> possibleActions;
        possibleActions = super.getPossibleActions(board, worker);
        List<Point> checkedActions= new ArrayList<>();
        for(Point p: possibleActions){
            if( !( board.isOccupied(p) || board.isThereDome(p) ) ){
                checkedActions.add(p);
            }
        }
        return checkedActions;
    }
}
