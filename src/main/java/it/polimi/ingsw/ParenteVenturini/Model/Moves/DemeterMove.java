package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class DemeterMove extends Move {

    private boolean hasWalked;
    private boolean hasEnded;
    private int numOfBuilding;
    private Point firstBuilding;

    public DemeterMove() {
        this.hasWalked = false;
        this.hasEnded = false;
        this.numOfBuilding = 0;
        this.firstBuilding = null;
    }

    @Override
    public void walk(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyWalkedException, endedMoveException {
        if(!hasEnded) {
            if (!hasWalked) {
                Action action = new BasicMovement();
                action.doAction(point, board, worker);
                hasWalked = true;
            } else throw new AlreadyWalkedException();
        }else throw new endedMoveException();
    }

    @Override
    public void build(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyBuiltException, OutOfOrderMoveException, endedMoveException {
        if(!hasEnded) {
            if (hasWalked) {
                if (numOfBuilding == 0) {
                    Action action = new BasicMovement();
                    action.doAction(point, board, worker);
                    firstBuilding = point;
                    numOfBuilding++;
                }
                if (numOfBuilding == 1) {
                    if (!point.equals(firstBuilding)) {
                        Action action = new BasicConstruction();
                        action.doAction(point, board, worker);
                        hasEnded = true;
                    } else throw new IllegalMovementException();

                } else throw new AlreadyBuiltException();
            } else throw new OutOfOrderMoveException();
        }else throw  new endedMoveException();
    }

    @Override
    public List<Point> possibleMovements(Board board, Worker worker) {
        Action action = new BasicMovement();
        if(!hasEnded) {
            return action.getPossibleActions(board, worker);
        }
        else return null;
    }

    @Override
    public List<Point> possibleBuildings(Board board, Worker worker) {
        Action action = new BasicConstruction();
        if(!hasEnded) {
            if(!hasWalked) {
                if(numOfBuilding==0) {
                    return action.getPossibleActions(board, worker);
                }
                else{
                    List<Point> possiblePoints = action.getPossibleActions(board, worker);
                    possiblePoints.remove(firstBuilding);
                    return possiblePoints;
                }
            }else return null;
        }
        else return null;
    }
}
