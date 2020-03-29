package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBuildingException;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class AtlasContruction extends Action {
    @Override
    public void doAction(Point point, Board board, Worker worker) throws IllegalBuildingException {
        if(isValid(point, board, worker)){
            board.setDoom(point,true);
        }
        else throw new IllegalBuildingException();
    }

    @Override
    public boolean isValid(Point point, Board board, Worker worker) {
        return super.isValid(point, board, worker);
    }

    @Override
    public List<Point> getPossibleActions(Board board, Worker worker) {
        return super.getPossibleActions(board, worker);
    }
}
