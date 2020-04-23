package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NoPossibleActionException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;

import java.util.List;

public class MoveHandler {
    private final Match match;
    private boolean movement;
    private boolean construction;
    private boolean specialconstruction;

    public MoveHandler(Match match) {
        this.match=match;
        this.construction= false;
        this.movement= false;
        this.specialconstruction= false;

    }

    public void init(){
        this.construction= false;
        this.movement= false;
        this.specialconstruction= false;
    }

    public void doAction(String nickname, Point x) throws NotYourTurnException, OpponentEffectException, AlreadyBuiltException, IllegalBuildingException, IllegalMovementException, NotPossibleEndMoveException, AlreadyWalkedException, endedMoveException, OutOfOrderMoveException {
        if(movement) {
            doMovement(nickname,x);
        }
        else if(construction) {
            doBuilding(nickname, x);
        }
        else if(specialconstruction){
            doSpecialBuilding(nickname, x);
        }
    }

    public void doMovement(String nickname, Point x) throws NotYourTurnException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, NotPossibleEndMoveException, AlreadyWalkedException, AlreadyBuiltException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            match.getTurn().getCurrentPlayer().walk(x);
        }
        else{
            throw new NotYourTurnException();
        }

    }

    public void doBuilding(String nickname, Point x) throws NotYourTurnException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, OutOfOrderMoveException, NotPossibleEndMoveException, AlreadyBuiltException, AlreadyWalkedException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            match.getTurn().getCurrentPlayer().build(x);
        }
        else{
            throw new NotYourTurnException();
        }
    }

    public void doSpecialBuilding(String nickname, Point x) throws NotYourTurnException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            match.getTurn().getCurrentPlayer().specialBuild(x);
        }
        else{
            throw new NotYourTurnException();
        }
    }

    public void doEndMove(String nickname) throws NotYourTurnException, NotPossibleEndMoveException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            match.getTurn().getCurrentPlayer().endMove();
        }
        else{
            throw new NotYourTurnException();
        }

    }

    public List<Point> getMovementsActions(String nickname) throws NotYourTurnException, NoPossibleActionException, AlreadyWalkedException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            movement= true;
            if(!match.getTurn().getCurrentPlayer().getPossibleMovements().isEmpty()) {
                return match.getTurn().getCurrentPlayer().getPossibleMovements();
            }
            else throw new NoPossibleActionException();
        }
        else{
            throw new NotYourTurnException();
        }
    }

    public List<Point> getConstructionActions(String nickname) throws NotYourTurnException, NoPossibleActionException, OutOfOrderMoveException, AlreadyBuiltException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            construction= true;
            if(!match.getTurn().getCurrentPlayer().getPossibleBuildings().isEmpty()) {
                return match.getTurn().getCurrentPlayer().getPossibleBuildings();
            }
            else{
                throw new NoPossibleActionException();
            }
        }
        else{
            throw new NotYourTurnException();
        }
    }

    public List<Point> getSpecialConstructionActions(String nickname) throws NotYourTurnException, NoPossibleActionException, OutOfOrderMoveException, AlreadyBuiltException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            specialconstruction= true;
            if(!match.getTurn().getCurrentPlayer().getPossibleBuildings().isEmpty()) {
                return match.getTurn().getCurrentPlayer().getPossibleBuildings();
            }
            else throw new NoPossibleActionException();
        }
        else{
            throw new NotYourTurnException();
        }
    }
}
