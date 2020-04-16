package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public class AthenaEffect implements OpponentEffect {

    //todo correct removeMovementPoints

    @Override
    public List<Point> removeMovementPoints(List<Point> movements, Point actualPoint, Board board) {
        List<Point> futureMovements = new ArrayList<>(movements);
        int level = board.blockLevel(actualPoint);
        for(Point p: futureMovements){
            if(board.blockLevel(p)>level)
                futureMovements.remove(p);
        }
        return futureMovements;
    }

    @Override
    public List<Point> removeConstructionPoints(List<Point> movements, Point actualPoint, Board board) {
        return movements;
    }

    @Override
    public boolean isMovementValid(Point nextPoint, Point actualPoint, Board board) {
        return board.blockLevel(actualPoint) >= board.blockLevel(nextPoint);
    }

    @Override
    public boolean isConstructionValid(Point nextPoint, Point actualPoint, Board board) {
        return true;
    }
}
