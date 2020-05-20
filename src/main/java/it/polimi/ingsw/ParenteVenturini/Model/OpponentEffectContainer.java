package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import javafx.scene.effect.Effect;

import java.util.ArrayList;
import java.util.List;

/** this class contains the Opponent Effect active and the Effect that will be activated the next turn */
public class OpponentEffectContainer {

    /** active opponent effects */
    List<OpponentEffect> nextTurn = new ArrayList<>();
    /** effect that are activating the next turn */
    List<OpponentEffect> thisTurn = new ArrayList<>();

    /**
     * insert the effect in the container
     * @param effect the effect that must be added
     */
    public void addEffect(OpponentEffect effect){
        nextTurn.add(effect);
        //System.out.println("Ho aggiunto un effetto");
    }

    /**
     * remove the effect from the container
     * @param effect the effect that must be removed
     */
    public void removeEffect(OpponentEffect effect){
        OpponentEffect opponentEffect = null;
        for(OpponentEffect eff: thisTurn){
            if(eff.equals(effect))
                opponentEffect = eff;
        }
        if(opponentEffect != null)
            thisTurn.remove(opponentEffect);

        OpponentEffect nextOpponentEffect = null;
        for(OpponentEffect eff: nextTurn){
            if(eff.equals(effect))
                nextOpponentEffect = eff;
        }
        if(nextOpponentEffect != null)
            nextTurn.remove(nextOpponentEffect);
    }

    /**
     * switch to a new turn
     */
    public void switchToNewTurn(){
        thisTurn = nextTurn;
        nextTurn = new ArrayList<>();
    }

    /**
     * get the list of active effects
     * @return the list of active effects
     */
    public List<OpponentEffect> getActiveEffects(){
        return thisTurn;
    }

    /**
     * remove the points disabled by the opponent's effects
     * @param possiblePoints list of points where the worker can walk
     * @param yourPosition the worker position
     * @param yourEffect the worker effect
     * @param board reference to the board
     * @return list of points where the worker can walk after the opponent's effect checking
     */
    public List<Point> removeMovementPoint(List<Point> possiblePoints, Point yourPosition, OpponentEffect yourEffect, Board board ){
        List<Point> tempPossiblePoints = new ArrayList<>(possiblePoints);
        for(OpponentEffect eff: getActiveEffects()){
            if(!eff.equals(yourEffect)){
                possiblePoints = eff.removeMovementPoints(possiblePoints, yourPosition, board);
            }
        }
        return possiblePoints;
    }

    /**
     * remove the points disabled by the opponent's effects
     * @param possiblePoints list of points where the worker can build
     * @param yourPosition the worker position
     * @param yourEffect the worker effect
     * @param board reference to the board
     * @return list of points where the worker can build after the opponent's effect checking
     */
    public List<Point> removeConstructionPoint(List<Point> possiblePoints, Point yourPosition, OpponentEffect yourEffect, Board board ){
        List<Point> tempPossiblePoints = new ArrayList<>(possiblePoints);
        for(OpponentEffect eff: getActiveEffects()){
            if(!eff.equals(yourEffect)){
                possiblePoints = eff.removeConstructionPoints(possiblePoints, yourPosition, board);
            }
        }
        return possiblePoints;
    }

    /**
     * check if the worker can walk to a point, even if there are opponent's effect
     * @param nextPoint the point the worker want to walk to
     * @param worker the worker
     * @param board board reference
     * @return true if the worker can walk to that point
     */

    public boolean checkMovementPoint(Point nextPoint, Worker worker, Board board ){
        for(OpponentEffect eff: getActiveEffects()){
            if(!eff.equals(worker.getEffect()) ){
                if(!eff.isMovementValid(nextPoint, worker.getPosition(), board))
                    return false;
            }
        }
        return true;
    }

    /**
     *
     * check if the worker can build in a point, even if there are opponent's effect
     * @param nextPoint the point the worker want to build in
     * @param worker the worker
     * @param board board reference
     * @return true if the worker can build in that point
     */

    public boolean checkConstructionPoint(Point nextPoint, Worker worker, Board board ){
        for(OpponentEffect eff: getActiveEffects()){
            if(!eff.equals(worker.getEffect())){
                if(!eff.isConstructionValid(nextPoint, worker.getPosition(), board))
                    return false;
            }
        }
        return true;
    }

}
