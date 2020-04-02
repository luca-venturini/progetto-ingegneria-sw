package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.AtlasContruction;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffectContainer;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class AtlasMove extends Move {

    private boolean hasWalked;
    private boolean hasEnded;

    public AtlasMove() {
        this.hasWalked = false;
        this.hasEnded = false;
    }


    @Override
    public void walk(Point point, Board board, Worker worker, OpponentEffectContainer oppEff) throws IllegalBuildingException, IllegalMovementException, AlreadyWalkedException, endedMoveException {
        if(!hasEnded) {
            if (!hasWalked) {
                Action action = new BasicMovement();
                action.doAction(point, board, worker);
                hasWalked = true;
            } else throw new AlreadyWalkedException();
        }else throw new endedMoveException();
    }

    @Override
    public void build(Point point, Board board, Worker worker, OpponentEffectContainer oppEff) throws IllegalBuildingException, IllegalMovementException, OutOfOrderMoveException, endedMoveException {
        if(!hasEnded) {
            if (hasWalked) {
                Action action = new BasicConstruction();
                action.doAction(point, board, worker);
                hasEnded = true;
            } else {
                throw new OutOfOrderMoveException();
            }
        }else throw new endedMoveException();
    }

    @Override
    public void specialBuild(Point point, Board board, Worker worker, OpponentEffectContainer oppEff) throws IllegalBuildingException, IllegalMovementException, OutOfOrderMoveException, endedMoveException {
        if(!hasEnded) {
            if (hasWalked) {
                Action action = new AtlasContruction();
                action.doAction(point, board, worker);
                hasEnded = true;
            } else {
                throw new OutOfOrderMoveException();
            }
        }else throw new endedMoveException();
    }

    @Override
    public List<Point> possibleMovements(Board board, Worker worker, OpponentEffectContainer oppEff) {
        Action action = new BasicMovement();
        List<Point> possiblePoints = action.getPossibleActions(board, worker);
        return oppEff.removeMovementPoint(possiblePoints, worker.getPosition(), worker.getEffect(), board);
    }

    @Override
    public java.util.List<Point> possibleBuildings(Board board, Worker worker, OpponentEffectContainer oppEff) {
        Action action = new BasicConstruction();
        List<Point> possiblePoints = action.getPossibleActions(board, worker);
        return oppEff.removeConstructionPoint(possiblePoints, worker.getPosition(), worker.getEffect(), board);
    }
}
