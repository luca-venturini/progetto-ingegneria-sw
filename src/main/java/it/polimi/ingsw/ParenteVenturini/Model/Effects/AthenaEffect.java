package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public class AthenaEffect implements OpponentEffect {

    private final String effectCode = "AthenaEffect";

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

    @Override
    public List<Point> removeConstructionPoints(List<Point> movements, Point actualPoint, Board board) {
        return movements;
    }

    @Override
    public boolean isMovementValid(Point nextPoint, Point beforePoint, Board board) {
        return board.blockLevel(beforePoint) >= board.blockLevel(nextPoint);
    }

    @Override
    public boolean isConstructionValid(Point nextPoint, Point actualPoint, Board board) {
        return true;
    }

    @Override
    public boolean isWinEffect() {
        return false;
    }

    @Override
    public boolean isMovementEffectEnabled(Point nextPoint , Point beforePoint, Board board) {
        //System.out.println("nextPoint: "+nextPoint+" - livello: "+board.blockLevel(nextPoint));
        //System.out.println("beforePoint: "+beforePoint+" - livello: "+board.blockLevel(beforePoint));
        //System.out.println("beforePoint<nextPoint: "+ (board.blockLevel(beforePoint) < board.blockLevel(nextPoint)));
        return board.blockLevel(beforePoint) < board.blockLevel(nextPoint);
    }

    @Override
    public boolean isConstructionEffectEnabled(Point nextPoint , Point beforePoint, Board board) {
        return false;
    }

    @Override
    public boolean isWinner(Board board, Worker worker) {
        return false;
    }

    @Override
    public String getEffectCode() {
        return effectCode;
    }

    @Override
    public boolean equals(OpponentEffect o){
        if(o == null)
            return false;
        return effectCode.equals(o.getEffectCode());// == o.getEffectCode();
    }
}
