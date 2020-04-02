package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.ApolloMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffectContainer;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;
import org.graalvm.compiler.virtual.phases.ea.EffectList;

import java.util.List;

public class ApolloMove extends Move {

    private boolean hasWalked;
    private boolean hasEnded;

    public ApolloMove() {
        hasWalked=false;
        hasEnded=false;
    }

    @Override
    public void walk(Point point, Board board, Worker worker, OpponentEffectContainer oppEff) throws AlreadyWalkedException, IllegalBuildingException, IllegalMovementException, endedMoveException, OpponentEffectException {
        if(!hasEnded) {
            if (!hasWalked) {
                if(!possibleMovements(board, worker, oppEff).contains(point))
                    throw new OpponentEffectException();
                Action action = new ApolloMovement();
                action.doAction(point, board, worker);
                hasWalked = true;
            } else throw new AlreadyWalkedException();
        }else throw new endedMoveException();
    }

    @Override
    public void build(Point point, Board board, Worker worker, OpponentEffectContainer oppEff) throws OutOfOrderMoveException, IllegalBuildingException, IllegalMovementException, endedMoveException, OpponentEffectException {
        if(!hasEnded) {
            if (hasWalked) {
                if(!possibleMovements(board, worker, oppEff).contains(point))
                    throw new OpponentEffectException();
                Action action = new BasicConstruction();
                action.doAction(point, board, worker);
                hasEnded = true;
            } else {
                throw new OutOfOrderMoveException();
            }
        }else throw new endedMoveException();
    }

    @Override
    public List<Point> possibleMovements(Board board, Worker worker, OpponentEffectContainer oppEff) {
        Action action = new ApolloMovement();
        if(!hasEnded) {
            List<Point> possiblePoints = action.getPossibleActions(board, worker);
            return oppEff.removeMovementPoint(possiblePoints, worker.getPosition(), worker.getEffect(), board);
        }
        else return null;
    }

    @Override
    public List<Point> possibleBuildings(Board board, Worker worker, OpponentEffectContainer oppEff) {
        Action action = new BasicConstruction();
        if(!hasEnded) {
            List<Point> possiblePoints = action.getPossibleActions(board, worker);
            return oppEff.removeConstructionPoint(possiblePoints, worker.getPosition(), worker.getEffect(), board);
        }
        else return null;
    }


}
