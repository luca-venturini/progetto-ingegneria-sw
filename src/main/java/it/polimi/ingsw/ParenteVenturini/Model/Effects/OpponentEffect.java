package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public interface OpponentEffect {

    /**
     * remove the movement points that are not allowed by this effect
     * @param movements list of points
     * @param actualPoint the worker actual point
     * @param board the used board
     * @return list of possibile points after this effect checking
     */
    List<Point> removeMovementPoints(List<Point> movements, Point actualPoint, Board board);

    /**
     * remove the building points that are not allowed by this effect
     * @param movements list of points
     * @param actualPoint the worker actual point
     * @param board the used board
     * @return list of possibile points after this effect checking
     */
    List<Point> removeConstructionPoints(List<Point> movements, Point actualPoint, Board board);

    /**
     * check if the next point where you want to walk is valid, based on the current point
     * @param nextPoint the next point
     * @param actualPoint the actual point
     * @param board the used board
     * @return true if the next point is valid
     */
    boolean isMovementValid(Point nextPoint, Point actualPoint, Board board);

    /**
     * check if the next point where you want to build is valid, based on the current point
     * @param nextPoint the next point
     * @param actualPoint the actual point
     * @param board the used board
     * @return true if the next point is valid
     */
    boolean isConstructionValid(Point nextPoint, Point actualPoint, Board board);

    /**
     * check if the player effect has been activated thatks to your walk
     * @param beforePoint the point before you walked
     * @param nextPoint the actual worker point
     * @param board the used board
     * @return true if the effects has been activated
     */
    boolean isMovementEffectEnabled(Point beforePoint, Point nextPoint, Board board);

    /**
     * check if the player effect has been activated thatks to your build
     * @param beforePoint the point before you built
     * @param nextPoint the actual worker point
     * @param board the used board
     * @return true if the effects has been activated
     */
    boolean isConstructionEffectEnabled(Point beforePoint, Point nextPoint, Board board);

    /**
     * check if the effect refers to a movement or a win
     * @return true if it refers to a win
     */
    boolean isWinEffect();

    /**
     * check if the worker can win even if the effect is active
     * @param board the used board
     * @param worker the worker
     * @return true if the worker can win
     */
    boolean isWinner(Board board, Worker worker);

    /**
     * the code of the effect
     * @return the code of the effect
     */
    String getEffectCode();

    /**
     * check if two effects are equal
     * @param opponentEffect the other effect
     * @return true if the effects are equal
     */
    boolean equals(OpponentEffect opponentEffect);
}
