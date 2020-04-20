package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.ZeusConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public class ZeusMove extends Move {

    private int numOfBuilding;
    private Point firstBuilding;

    public ZeusMove() {
        this.hasWalked = false;
        this.hasBuilt = false;
        this.hasEnded = false;
        this.numOfBuilding = 0;
        this.firstBuilding = null;
    }

    @Override
    public void walk(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyWalkedException, endedMoveException, AlreadyBuiltException {
        if(!hasEnded) {
            if(!hasBuilt) {
                if (!hasWalked) {
                    Action action = new BasicMovement();
                    action.doAction(point, board, worker);
                    hasWalked = true;
                } else throw new AlreadyWalkedException();
            }else throw new AlreadyBuiltException();
        }else throw new endedMoveException();
    }

    @Override
    public void build(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, OutOfOrderMoveException, endedMoveException, AlreadyBuiltException {
        if(!hasEnded) {
            if( !hasBuilt) {
                if (hasWalked) {
                    Action action = new ZeusConstruction();
                    action.doAction(point, board, worker);
                    hasBuilt = true;
                    hasEnded = true;
                } else throw new OutOfOrderMoveException();
            }else throw new AlreadyBuiltException();
        }else throw new endedMoveException();
    }

    @Override
    public List<Point> possibleMovements(Board board, Worker worker) {
        Action action = new BasicMovement();
        if(!hasEnded && !hasBuilt && !hasWalked) {
            return action.getPossibleActions(board, worker);
        }
        else return null;
    }

    @Override
    public List<Point> possibleBuildings(Board board, Worker worker) {
        Action action = new ZeusConstruction();
        if(!hasEnded  && !hasBuilt && hasWalked) {
            List<Point> possibleBuildings = action.getPossibleActions(board, worker);
            return possibleBuildings;
        }
        else return null;
    }


}
