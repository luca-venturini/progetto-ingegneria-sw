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

/**
 * Handle the moves of this Card's owner
 */
public class ZeusMove extends Move {

    /** keep track of the number of building the player has already done */
    private int numOfBuilding;
    /** used to check if the player has already built */
    private Point firstBuilding;

    /**
     * init the move
     */
    public ZeusMove() {
        this.hasWalked = false;
        this.hasBuilt = false;
        this.hasEnded = false;
        this.numOfBuilding = 0;
        this.firstBuilding = null;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> possibleMovements(Board board, Worker worker) throws AlreadyWalkedException {
        Action action = new BasicMovement();
        if(!hasEnded && !hasBuilt && !hasWalked) {
            return action.getPossibleActions(board, worker);
        }
        else throw new AlreadyWalkedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> possibleBuildings(Board board, Worker worker) throws OutOfOrderMoveException, AlreadyBuiltException {
        Action action = new ZeusConstruction();
        if(!hasEnded  && !hasBuilt) {
            if(hasWalked) {
                return action.getPossibleActions(board, worker);
            }
            else throw new OutOfOrderMoveException();
        }
        else throw new AlreadyBuiltException();
    }


}
