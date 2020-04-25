package it.polimi.ingsw.ParenteVenturini.Model.Moves;


import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;


public abstract class Move {

    protected boolean hasWalked;
    protected boolean hasBuilt;
    protected boolean hasEnded;

    public abstract void walk(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyWalkedException, endedMoveException, AlreadyBuiltException;

    public abstract void build(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, AlreadyBuiltException, OutOfOrderMoveException, endedMoveException, AlreadyWalkedException;

    public void specialBuild(Point point, Board board, Worker worker) throws IllegalBuildingException, IllegalMovementException, OutOfOrderMoveException, endedMoveException, AlreadyBuiltException, AlreadyWalkedException {
        build(point, board, worker);
    }

    public abstract List<Point> possibleMovements(Board board, Worker worker) throws AlreadyWalkedException;

    public abstract List<Point> possibleBuildings(Board board, Worker worker) throws OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException;

    public boolean forcedMovement(){
        return !hasWalked;
    }

    public boolean forcedBuilding(){
        return hasWalked && !hasBuilt;
    }

    public boolean getHasWalkedandBuilt(){
        return hasWalked && hasBuilt;
    }

    public boolean getHasEnded(){
        return hasEnded;
    }

}
