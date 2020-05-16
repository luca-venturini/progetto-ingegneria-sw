package it.polimi.ingsw.ParenteVenturini.Model.Moves;


import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;


/**
 * Handle the moves of Card's owner
 */
public abstract class Move {

    /** variable that check if the player has already walked */
    protected boolean hasWalked;
    /** variable that check if the player has already built */
    protected boolean hasBuilt;
    /** variable that check if the player can't do other actions */
    protected boolean hasEnded;

    /**
     * the worker moves in another point
     *
     * @param point the point where yu want to move
     * @param board the board used
     * @param worker the selected worker
     * @throws AlreadyWalkedException exception thrown if you have already walked
     * @throws IllegalBuildingException exception thrown if you can't build
     * @throws IllegalMovementException exception thrown if you can't walk
     * @throws endedMoveException exception thrown if you have finished
     * @throws AlreadyBuiltException exception thrown if you have already built
     */
    public abstract void walk(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyWalkedException, endedMoveException, AlreadyBuiltException;

    /**
     * the worker can build in another point
     *
     * @param point the point where yu want to move
     * @param board the board used
     * @param worker the selected worker
     * @throws IllegalBuildingException exception thrown if you can't build
     * @throws IllegalMovementException exception thrown if you can't walk
     * @throws AlreadyBuiltException exception thrown if you have already built
     * @throws OutOfOrderMoveException thrown if you can't walk
     * @throws endedMoveException thrown if you have ended your turn
     * @throws AlreadyWalkedException thrown if you have already walked
     */

    public abstract void build(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyBuiltException, OutOfOrderMoveException, endedMoveException, AlreadyWalkedException;

    /**
     * the worker can use his special building effect, if he doesn't have one he will do a normal building
     *
     * @param point the point where yu want to move
     * @param board the board used
     * @param worker the selected worker
     * @throws IllegalBuildingException exception thrown if you can't build
     * @throws IllegalMovementException exception thrown if you can't walk
     * @throws AlreadyBuiltException exception thrown if you have already built
     * @throws OutOfOrderMoveException thrown if you can't walk
     * @throws endedMoveException thrown if you have ended your turn
     * @throws AlreadyWalkedException thrown if you have already walked
     */
    public void specialBuild(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, OutOfOrderMoveException, endedMoveException, AlreadyBuiltException, AlreadyWalkedException {
        build(point, board, worker);
    }

    /**
     * create a list of possible points where the worker can walk
     *
     * @param board the board used
     * @param worker the selected worker
     * @return list of possible points where the worker can walk
     * @throws AlreadyWalkedException thrown if the worker can't walk
     */

    public abstract List<Point> possibleMovements(Board board, Worker worker) throws AlreadyWalkedException;

    /**
     * create a list of possible points where the worker can build
     *
     * @param board the board used
     * @param worker the selected worker
     * @return list of possible points where the worker can build
     * @throws OutOfOrderMoveException thrown if you can't walk
     * @throws AlreadyBuiltException exception thrown if you have already built
     * @throws AlreadyWalkedException thrown if you have already walked
     */
    public abstract List<Point> possibleBuildings(Board board, Worker worker) throws OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException;


    /**
     * check if you are forced to walk
     *
     * @return true if you are forced to walk
     */
    public boolean forcedMovement(){
        return !hasWalked;
    }

    /**
     * check if you are forced to build
     *
     * @return true if you are forced to build
     */

    public boolean forcedBuilding(){
        return hasWalked && !hasBuilt;
    }

    /**
     * check if you have walked and built
     * @return true if you have walked and build
     */

    public boolean getHasWalkedandBuilt(){
        return hasWalked && hasBuilt;
    }

    /**
     * check if you have ended all the possible moves
     * @return true if you have ended all the possible moves
     */

    public boolean getHasEnded(){
        return hasEnded;
    }

}
