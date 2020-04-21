package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;

public class MoveHandler {
    private Match match;
    private boolean movement;
    private boolean construction;

    public MoveHandler(Match match) {
        this.match=match;
        this.construction= false;
        this.movement= false;
    }

    public void doAction(String nickname, Point x) throws NotYourTurnException, OpponentEffectException, AlreadyBuiltException, IllegalBuildingException, IllegalMovementException, NotPossibleEndMoveException, AlreadyWalkedException, endedMoveException, OutOfOrderMoveException {
        if(movement == true) {
            doMovement(nickname,x);
        }
        else if(construction == true) {
            doBuilding(nickname, x);
        }
    }

    public void doMovement(String nickname, Point x) throws NotYourTurnException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, NotPossibleEndMoveException, AlreadyWalkedException, AlreadyBuiltException {
        if(match.getTurn().getCurrentPlayer().equals(nickname)) {
            match.getTurn().getCurrentPlayer().walk(x);
        }
        else{
            throw new NotYourTurnException();
        }

    }

    public void doBuilding(String nickname, Point x) throws NotYourTurnException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, OutOfOrderMoveException, NotPossibleEndMoveException, AlreadyBuiltException {
        if(match.getTurn().getCurrentPlayer().equals(nickname)) {
            match.getTurn().getCurrentPlayer().build(x);
        }
        else{
            throw new NotYourTurnException();
        }
    }

    public void doEndMove(String nickname) throws NotYourTurnException, NotPossibleEndMoveException {
        if(match.getTurn().getCurrentPlayer().equals(nickname)) {
            match.getTurn().getCurrentPlayer().endMove();
        }
        else{
            throw new NotYourTurnException();
        }

    }

    public void getMovementsActions(String nickname) throws NotYourTurnException {
        if(match.getTurn().getCurrentPlayer().equals(nickname)) {
            match.getTurn().getCurrentPlayer().getPossibleMovements();
            movement= true;
        }
        else{
            throw new NotYourTurnException();
        }
    }

    public void getConstructionActions(String nickname) throws NotYourTurnException {
        if(match.getTurn().getCurrentPlayer().equals(nickname)) {
            match.getTurn().getCurrentPlayer().getPossibleBuildings();
            construction= true;
        }
        else{
            throw new NotYourTurnException();
        }
    }
}
