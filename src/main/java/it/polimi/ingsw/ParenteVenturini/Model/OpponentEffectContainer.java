package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;

import java.util.ArrayList;
import java.util.List;

public class OpponentEffectContainer {

    List<OpponentEffect> nextTurn = new ArrayList<>();
    List<OpponentEffect> thisTurn = new ArrayList<>();

    public void addEffect(OpponentEffect effect){
        nextTurn.add(effect);
    }

    public void switchToNewTurn(){
        thisTurn = nextTurn;
        nextTurn = null;
    }

    public List<OpponentEffect> getActiveEffects(){
        return thisTurn;
    }

    public List<Point> removeMovementPoint(List<Point> possiblePoints, Point yourPosition, OpponentEffect yourEffect, Board board ){
        List<Point> tempPossiblePoints = new ArrayList<>(possiblePoints);
        for(OpponentEffect eff: getActiveEffects()){
            if(!eff.equals(yourEffect)){
                possiblePoints = eff.removeMovementPoints(possiblePoints, yourPosition, board);
            }
        }
        return possiblePoints;
    }

    public List<Point> removeConstructionPoint(List<Point> possiblePoints, Point yourPosition, OpponentEffect yourEffect, Board board ){
        List<Point> tempPossiblePoints = new ArrayList<>(possiblePoints);
        for(OpponentEffect eff: getActiveEffects()){
            if(!eff.equals(yourEffect)){
                possiblePoints = eff.removeConstructionPoints(possiblePoints, yourPosition, board);
            }
        }
        return possiblePoints;
    }

    public boolean checkMovementPoint(Point nextPoint, Worker worker, Board board ){
        for(OpponentEffect eff: getActiveEffects()){
            if(!eff.equals(worker.getEffect()) ){
                if(!eff.isMovementValid(nextPoint, worker.getPosition(), board))
                    return false;
            }
        }
        return true;
    }

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
