package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.MinotaurMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffectContainer;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class MinotaurMove extends Move {

    private boolean hasWalked;
    private boolean hasBuilt;

    @Override
    public void walk(Point point, Board board, Worker worker, OpponentEffectContainer oppEff) throws IllegalMovementException, AlreadyWalkedException, IllegalBuildingException {
        if(!hasWalked){
            Action action = new MinotaurMovement();
            action.doAction(point, board, worker);
            hasWalked = true;
        }
        else throw new AlreadyWalkedException();
    }

    @Override
    public void build(Point point, Board board, Worker worker, OpponentEffectContainer oppEff) throws IllegalBuildingException, IllegalMovementException, AlreadyBuiltException, OutOfOrderMoveException {
        if(hasWalked) {
            if(!hasBuilt) {
                Action action = new BasicConstruction();
                action.doAction(point, board, worker);
                hasBuilt = true;
            }
            else
                throw new AlreadyBuiltException();
        }
        else
            throw new OutOfOrderMoveException();
    }

    @Override
    public List<Point> possibleMovements(Board board, Worker worker, OpponentEffectContainer oppEff) {
        Action action = new MinotaurMovement();
        List<Point> possiblePoints = action.getPossibleActions(board, worker);
        return oppEff.removeMovementPoint(possiblePoints, worker.getPosition(), worker.getEffect(), board);
    }

    @Override
    public List<Point> possibleBuildings(Board board, Worker worker, OpponentEffectContainer oppEff) {
        Action action = new BasicMovement();
        List<Point> possiblePoints = action.getPossibleActions(board, worker);
        return oppEff.removeConstructionPoint(possiblePoints, worker.getPosition(), worker.getEffect(), board);
    }
}
