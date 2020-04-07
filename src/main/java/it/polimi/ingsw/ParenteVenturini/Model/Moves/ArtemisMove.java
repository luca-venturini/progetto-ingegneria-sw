package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicConstruction;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.BasicMovement;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class ArtemisMove extends Move {

    private boolean hasWalked;
    private boolean hasEnded;
    private int numOfMovements;
    private Point firstMovement;

    public ArtemisMove() {
        this.hasWalked = false;
        this.hasEnded = false;
        this.numOfMovements = 0;
        this.firstMovement = null;
    }

    @Override
    public void walk(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyWalkedException, endedMoveException {
        if(!hasEnded) {
            if (!hasWalked) {
                if (numOfMovements == 0) {
                    Action action = new BasicMovement();
                    action.doAction(point, board, worker);
                    firstMovement = point;
                    numOfMovements++;
                }
                if (numOfMovements == 1) {
                    if (!point.equals(firstMovement)) {
                        Action action = new BasicMovement();
                        action.doAction(point, board, worker);
                        hasWalked = true;
                    } else throw new IllegalMovementException();

                } else throw new AlreadyWalkedException();
            } else throw new AlreadyWalkedException();
        }else throw  new endedMoveException();
    }

    @Override
    public void build(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, OutOfOrderMoveException, endedMoveException {
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
    public java.util.List<Point> possibleMovements(Board board, Worker worker) {
        Action action = new BasicMovement();
        if(!hasEnded) {
            if(!hasWalked) {
                if(numOfMovements==0) {
                    return action.getPossibleActions(board, worker);
                }
                else{
                    List<Point> possiblePoints = action.getPossibleActions(board, worker);
                    possiblePoints.remove(firstMovement);
                    return possiblePoints;
                }
            }else return null;
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
