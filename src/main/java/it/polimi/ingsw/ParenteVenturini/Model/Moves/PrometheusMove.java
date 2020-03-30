package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class PrometheusMove extends Move {

    private boolean hasWalked;
    private boolean hasBuilt;
    private boolean specialEffectAlreadyActivated;

    @Override
    public void walk(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyWalkedException {
        if(!hasWalked){
            Action action = new BasicMovement();
            action.doAction(point, board, worker);
            hasWalked = true;
        }
        else throw new AlreadyWalkedException();
    }

    @Override
    public void build(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyBuiltException, OutOfOrderMoveException {
        if(!hasWalked && !specialEffectAlreadyActivated && canUseSpecialEffect(board, worker)){
            Action action = new BasicConstruction();
            action.doAction(point, board, worker);
            specialEffectAlreadyActivated = true;
        }
        else if(!hasBuilt && hasWalked){
            Action action = new BasicConstruction();
            action.doAction(point, board, worker);
            hasBuilt = true;
        }
    }

    @Override
    public List<Point> possibleMovements(Board board, Worker worker) {
        Action action = new BasicMovement();
        return action.getPossibleActions(board, worker);
    }

    @Override
    public List<Point> possibleBuildings(Board board, Worker worker) {
        Action action = new BasicConstruction();
        return action.getPossibleActions(board, worker);
    }

    private boolean canUseSpecialEffect(Board board, Worker worker){
        int level = board.blockLevel(worker.getPosition());
        List<Point> possiblePoints = possibleMovements(board, worker);
        for(Point p: possiblePoints){
            if(board.blockLevel(p)>level)
                return false;
        }
        return true;
    }
}
