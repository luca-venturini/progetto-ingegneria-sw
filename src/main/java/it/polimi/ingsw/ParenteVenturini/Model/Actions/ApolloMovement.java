package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class ApolloMovement extends BasicMovement {

    @Override
    public void doAction(Point point, Board board, Worker worker) {
        super.doAction(point, board, worker);
    }

    @Override
    public boolean isValid(Point point, Board board, Worker worker) {
        return super.isValid(point, board, worker);
    }

    @Override
    public List<Point> getPossibleMovement(Board board, Worker worker) {
        return super.getPossibleMovement(board, worker);
    }
}
