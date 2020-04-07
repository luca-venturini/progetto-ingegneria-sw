package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.ApolloMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class ApolloMove extends Move {

    public ApolloMove() {
        hasWalked=false;
        hasEnded=false;
    }

    @Override
    public void walk(Point point, Board board, Worker worker) throws AlreadyWalkedException, IllegalBuildingException, IllegalMovementException, endedMoveException {
        if(!hasEnded) {
            if (!hasWalked) {
                Action action = new ApolloMovement();
                action.doAction(point, board, worker);
                hasWalked = true;
            } else throw new AlreadyWalkedException();
        }else throw new endedMoveException();
    }

    @Override
    public void build(Point point, Board board, Worker worker) throws OutOfOrderMoveException, IllegalBuildingException, IllegalMovementException, endedMoveException {
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
    public List<Point> possibleMovements(Board board, Worker worker) {
        Action action = new ApolloMovement();
        if(!hasEnded) {
            return action.getPossibleActions(board, worker);
        }
        else return null;
    }

    @Override
    public List<Point> possibleBuildings(Board board, Worker worker) {
        Action action = new BasicConstruction();
        if(!hasEnded) {
            return action.getPossibleActions(board, worker);
        }
        else return null;
    }


}
