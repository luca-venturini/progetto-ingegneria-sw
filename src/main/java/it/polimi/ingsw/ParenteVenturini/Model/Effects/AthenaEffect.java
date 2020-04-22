package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public class AthenaEffect implements OpponentEffect {

    @Override
    public List<Point> removeMovementPoints(List<Point> movements, Point actualPoint, Board board) {
        List<Point> futureMovements = new ArrayList<>(movements);
        List<Point> checkedMovements= new ArrayList<>();
        int level = board.blockLevel(actualPoint);
        for(Point p: futureMovements){
            if(board.blockLevel(p)<=level)
                checkedMovements.add(p);
        }
        return checkedMovements;
    }

    @Override
    public List<Point> removeConstructionPoints(List<Point> movements, Point actualPoint, Board board) {
        return movements;
    }

    @Override
    public boolean isMovementValid(Point nextPoint, Point beforePoint, Board board) {
        return board.blockLevel(beforePoint) >= board.blockLevel(nextPoint);
        //return board.blockLevel(beforePoint) < board.blockLevel(nextPoint);
    }

    @Override
    public boolean isConstructionValid(Point nextPoint, Point actualPoint, Board board) {
        return false; //it was true
    }

    @Override
    public boolean isWinEffect() {
        return false;
    }

    @Override
    public boolean isEffectEnabled(Point beforePoint, Point nextPoint, Board board) {
        return board.blockLevel(beforePoint) < board.blockLevel(nextPoint);
    }

    @Override
    public boolean isWinner(Board board, Worker worker) {
        return false;
    }
}
