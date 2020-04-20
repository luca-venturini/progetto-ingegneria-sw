package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBuildingException;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public class ZeusConstruction extends Action {

    @Override
    public void doAction(Point point, Board board, Worker worker) throws IllegalBuildingException {
        if(isValid(point, board, worker)){
            int level;
            level=board.blockLevel(point)+1;
            try {
                board.setBlockLevel(point,level);
            } catch (IllegalBlockUpdateException e) {
                e.printStackTrace();
            }
        }
        else throw new IllegalBuildingException();
    }

    @Override
    public boolean isValid(Point point, Board board, Worker worker) {
        List<Point> possibleActions=getPossibleActions(board,worker);
        return checkValid(point,possibleActions);
    }

    @Override
    public List<Point> getPossibleActions(Board board, Worker worker) {
        List<Point> possibleActions=super.getPossibleActions(board, worker);
        List<Point> checkedActions= new ArrayList<>();
        for(Point p: possibleActions){
            if( !( board.isOccupied(p) || board.isThereDome(p) ) ){
                checkedActions.add(p);
            }
        }
        checkedActions.add(worker.getPosition());
        return checkedActions;
    }
}
