package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class HeraEffect implements OpponentEffect {

    private final String effectCode = "HeraEffect";

    @Override
    public List<Point> removeMovementPoints(List<Point> movements, Point actualPoint, Board board) {
        return null;
    }

    @Override
    public List<Point> removeConstructionPoints(List<Point> movements, Point actualPoint, Board board) {
        return null;
    }

    @Override
    public boolean isMovementValid(Point nextPoint, Point actualPoint, Board board) {
        return false;
    }

    @Override
    public boolean isConstructionValid(Point nextPoint, Point actualPoint, Board board) {
        return false;
    }

    @Override
    public boolean isWinEffect() {
        return true;
    }

    @Override
    public boolean isMovementEffectEnabled(Point beforePoint, Point nextPoint, Board board) {
        return false;
    }

    @Override
    public boolean isConstructionEffectEnabled(Point beforePoint, Point nextPoint, Board board) {
        return false;
    }

    @Override
    public boolean isWinner(Board board, Worker worker) {
        if(board.isPerimeterPoint(worker.getPosition()))
            return false;
        return true;
    }

    @Override
    public String getEffectCode() {
        return effectCode;
    }

    @Override
    public boolean equals(OpponentEffect o){
        if(o == null)
            return false;
        return effectCode.equals(o.getEffectCode());
    }
}
