package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

/**
 * Athena Opponent Effect
 */
public class AthenaEffect implements OpponentEffect {

    /** Athena Effect code */
    private final String effectCode = "AthenaEffect";

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> removeMovementPoints(List<Point> movements, Point actualPoint, Board board) {
        List<Point> futureMovements = new ArrayList<>(movements);
        List<Point> checkedMovements= new ArrayList<>();
        int level = board.blockLevel(actualPoint);
        //System.out.println("il tuo livello: "+level);
        for(Point p: futureMovements){
            if(board.blockLevel(p)<=level)
                checkedMovements.add(p);
        }
        return checkedMovements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Point> removeConstructionPoints(List<Point> movements, Point actualPoint, Board board) {
        return movements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMovementValid(Point nextPoint, Point beforePoint, Board board) {
        return board.blockLevel(beforePoint) >= board.blockLevel(nextPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConstructionValid(Point nextPoint, Point actualPoint, Board board) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWinEffect() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMovementEffectEnabled(Point nextPoint , Point beforePoint, Board board) {
        //System.out.println("nextPoint: "+nextPoint+" - livello: "+board.blockLevel(nextPoint));
        //System.out.println("beforePoint: "+beforePoint+" - livello: "+board.blockLevel(beforePoint));
        //System.out.println("beforePoint<nextPoint: "+ (board.blockLevel(beforePoint) < board.blockLevel(nextPoint)));
        return board.blockLevel(beforePoint) < board.blockLevel(nextPoint);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isConstructionEffectEnabled(Point nextPoint , Point beforePoint, Board board) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isWinner(Board board, Worker worker) {
        return false;
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
        return effectCode.equals(o.getEffectCode());// == o.getEffectCode();
    }
}
