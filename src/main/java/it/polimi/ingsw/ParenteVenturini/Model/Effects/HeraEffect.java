package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

/**
 * Hera Opponent Effect
 */
public class HeraEffect implements OpponentEffect {

    /** Hera Effect code */
    private final String effectCode = "HeraEffect";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> removeMovementPoints(List<Point> movements, Point actualPoint, Board board) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> removeConstructionPoints(List<Point> movements, Point actualPoint, Board board) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMovementValid(Point nextPoint, Point actualPoint, Board board) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConstructionValid(Point nextPoint, Point actualPoint, Board board) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWinEffect() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMovementEffectEnabled(Point beforePoint, Point nextPoint, Board board) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConstructionEffectEnabled(Point beforePoint, Point nextPoint, Board board) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWinner(Board board, Worker worker) {
        if(board.isPerimeterPoint(worker.getPosition()))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getEffectCode() {
        return effectCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(OpponentEffect o){
        if(o == null)
            return false;
        return effectCode.equals(o.getEffectCode());
    }
}
