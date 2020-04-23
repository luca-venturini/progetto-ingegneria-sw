package it.polimi.ingsw.ParenteVenturini.Model.Actions;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalMovementException;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public class ApolloMovement extends Action {
    @Override
    public void doAction(Point point, Board board, Worker worker) throws IllegalMovementException {
        if(isValid(point,board,worker)) {
            Worker otherWorker = board.findByPosition(point);
            worker.setPosition(point);
            if (otherWorker != null) {
                otherWorker.setPosition(worker.getLastPosition());
            }
        }
        else throw new IllegalMovementException();
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
            if( !board.isThereDome(p) && board.blockLevel(p) - board.blockLevel(worker.getPosition()) <= 1 && !p.equals(worker.getPosition()) ){
                checkedActions.add(p);
                Worker opponent = board.findByPosition(p);
                if(opponent != null){
                    Player pl = opponent.getPlayer();
                    if(pl != null && pl.equals(worker.getPlayer())){
                        checkedActions.remove(p);
                    }
                }
            }

        }
        return checkedActions;
    }
}
