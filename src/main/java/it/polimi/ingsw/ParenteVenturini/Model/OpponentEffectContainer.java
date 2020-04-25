package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;

import java.util.ArrayList;
import java.util.List;

public class OpponentEffectContainer {

    List<OpponentEffect> nextTurn = new ArrayList<>();
    List<OpponentEffect> thisTurn = new ArrayList<>();

    public void addEffect(OpponentEffect effect){
        nextTurn.add(effect);
        //System.out.println("Ho aggiunto un effetto");
    }

    public void removeEffect(OpponentEffect effect){
        thisTurn.remove(effect);
        nextTurn.remove(effect);
    }

    public void switchToNewTurn(){
        thisTurn = nextTurn;
        //System.out.println("grandezza thisTurn: "+thisTurn.size());
        nextTurn = new ArrayList<>();
        //System.out.println("grandezza nextTurn: "+nextTurn.size());
    }

    public List<OpponentEffect> getActiveEffects(){
        return thisTurn;
    }

    public List<Point> removeMovementPoint(List<Point> possiblePoints, Point yourPosition, OpponentEffect yourEffect, Board board ){
        //System.out.println("your effect: "+yourEffect);
        //System.out.println("Sono in removeMovementPoint");
        List<Point> tempPossiblePoints = new ArrayList<>(possiblePoints);
        for(OpponentEffect eff: getActiveEffects()){
            //System.out.println("Sono nel for di removeMovementPoint");
            if(!eff.equals(yourEffect)){
                //System.out.println("Sono prima if di removeMovementPoint");
                possiblePoints = eff.removeMovementPoints(possiblePoints, yourPosition, board);
                //System.out.println("Sono dopo if di removeMovementPoint");
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
