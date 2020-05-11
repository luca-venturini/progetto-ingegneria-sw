package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

/**
 * Handle the moves of this Card's owner
 */
public class PrometheusMove extends Move {

    private boolean specialEffectAlreadyActivated;

    public PrometheusMove() {
        hasWalked=false;
        hasBuilt=false;
        hasEnded=false;
        specialEffectAlreadyActivated=false;
    }

    @Override
    public void walk(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyWalkedException, endedMoveException {
        if(!hasEnded) {
            if (!hasWalked) {
                Action action = new BasicMovement();
                action.doAction(point, board, worker);
                hasWalked = true;
            } else throw new AlreadyWalkedException();
        }else throw  new endedMoveException();
    }

    @Override
    public void build(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, endedMoveException, AlreadyWalkedException {
        if(!hasEnded) {
            if (!hasWalked && !specialEffectAlreadyActivated && canUseSpecialEffect(board, worker)) {
                Action action = new BasicConstruction();
                action.doAction(point, board, worker);
                specialEffectAlreadyActivated = true;
            } else if (!hasBuilt && hasWalked) {
                Action action = new BasicConstruction();
                action.doAction(point, board, worker);
                hasBuilt = true;
                hasEnded = true;
            }
        }else throw new endedMoveException();
    }

    @Override
    public List<Point> possibleMovements(Board board, Worker worker) throws AlreadyWalkedException {
        Action action = new BasicMovement();
        if(!hasEnded && !hasWalked) {
            return action.getPossibleActions(board, worker);
        }
        else throw new AlreadyWalkedException();
    }

    @Override
    public List<Point> possibleBuildings(Board board, Worker worker) throws OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException {
        Action action = new BasicConstruction();
        if(!hasEnded && !hasBuilt) {
            if(hasWalked || !specialEffectAlreadyActivated && canUseSpecialEffect(board, worker)){
                return action.getPossibleActions(board, worker);
            }
            else throw new OutOfOrderMoveException();
        }
        else throw new AlreadyBuiltException();
    }

    @Override
    public boolean forcedMovement() {
        return !hasWalked && specialEffectAlreadyActivated;
    }

    private boolean canUseSpecialEffect(Board board, Worker worker) throws AlreadyWalkedException {
        int level = board.blockLevel(worker.getPosition());
        List<Point> possiblePoints = possibleMovements(board, worker);
        for(Point p: possiblePoints){
            if(board.blockLevel(p)>level && board.blockLevel(p)-level<=1)
                return false;
        }
        return true;
    }
}
