package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffectContainer;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class HeraEffect implements OpponentEffect {
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
    public boolean isEffectEnabled(Point beforePoint, Point nextPoint, Board board) {
        return false;
    }

    @Override
    public boolean isWinner(Board board, Worker worker) {
        if(board.isPerimeterPoint(worker.getPosition()))
            return false;
        return true;
    }
}
