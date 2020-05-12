package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NoPossibleActionException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;

import java.util.List;

/**
 * this class handle the moves during the game
 */
public class MoveHandler {
    /** the actual game */
    private final Match match;
    /** true if the player requests to walk */
    private boolean movement;
    /** true if the player requests to build */
    private boolean construction;
    /** true if the player requests to use specialBuilding */
    private boolean specialconstruction;

    /**
     * init the class
     * @param match the actual match
     */
    public MoveHandler(Match match) {
        this.match=match;
        this.construction= false;
        this.movement= false;
        this.specialconstruction= false;

    }

    /**
     * reset boolean values
     */
    public void init(){
        this.construction= false;
        this.movement= false;
        this.specialconstruction= false;
    }

    /**
     * discover if a walk movement has been set
     * @return true if the move is walk
     */
    public boolean isMovement() {
        return movement;
    }

    /**
     * do the selected action
     * @param nickname the nickname of the player
     * @param x the selected point
     * @throws NotYourTurnException exception thrown if you can't do the action, the name is self-explanatory
     * @throws OpponentEffectException exception thrown if you can't do the action, the name is self-explanatory
     * @throws AlreadyBuiltException exception thrown if you can't do the action, the name is self-explanatory
     * @throws IllegalBuildingException exception thrown if you can't do the action, the name is self-explanatory
     * @throws IllegalMovementException exception thrown if you can't do the action, the name is self-explanatory
     * @throws NotPossibleEndMoveException exception thrown if you can't do the action, the name is self-explanatory
     * @throws AlreadyWalkedException exception thrown if you can't do the action, the name is self-explanatory
     * @throws endedMoveException exception thrown if you can't do the action, the name is self-explanatory
     * @throws OutOfOrderMoveException exception thrown if you can't do the action, the name is self-explanatory
     */
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

    /**
     *
     * the selected worker walk
     * @param nickname the nickname of the player
     * @param x the selected point
     * @throws NotYourTurnException exception thrown if you can't do the action, the name is self-explanatory
     * @throws OpponentEffectException exception thrown if you can't do the action, the name is self-explanatory
     * @throws AlreadyBuiltException exception thrown if you can't do the action, the name is self-explanatory
     * @throws IllegalBuildingException exception thrown if you can't do the action, the name is self-explanatory
     * @throws IllegalMovementException exception thrown if you can't do the action, the name is self-explanatory
     * @throws NotPossibleEndMoveException exception thrown if you can't do the action, the name is self-explanatory
     * @throws AlreadyWalkedException exception thrown if you can't do the action, the name is self-explanatory
     * @throws endedMoveException exception thrown if you can't do the action, the name is self-explanatory
     */
    public void doMovement(String nickname, Point x) throws NotYourTurnException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, NotPossibleEndMoveException, AlreadyWalkedException, AlreadyBuiltException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            match.getTurn().getCurrentPlayer().walk(x);
        }
        else{
            throw new NotYourTurnException();
        }

    }

    /**
     *
     * the selected worker build
     * @param nickname the nickname of the player
     * @param x the selected point
     * @throws NotYourTurnException exception thrown if you can't do the action, the name is self-explanatory
     * @throws OpponentEffectException exception thrown if you can't do the action, the name is self-explanatory
     * @throws AlreadyBuiltException exception thrown if you can't do the action, the name is self-explanatory
     * @throws IllegalBuildingException exception thrown if you can't do the action, the name is self-explanatory
     * @throws IllegalMovementException exception thrown if you can't do the action, the name is self-explanatory
     * @throws NotPossibleEndMoveException exception thrown if you can't do the action, the name is self-explanatory
     * @throws AlreadyWalkedException exception thrown if you can't do the action, the name is self-explanatory
     * @throws endedMoveException exception thrown if you can't do the action, the name is self-explanatory
     */
    public void doBuilding(String nickname, Point x) throws NotYourTurnException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, OutOfOrderMoveException, NotPossibleEndMoveException, AlreadyBuiltException, AlreadyWalkedException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            match.getTurn().getCurrentPlayer().build(x);
        }
        else{
            throw new NotYourTurnException();
        }
    }

    /**
     *
     * the selected worker do the special building
     * @param nickname the nickname of the player
     * @param x the selected point
     * @throws NotYourTurnException exception thrown if you can't do the action, the name is self-explanatory
     * @throws OpponentEffectException exception thrown if you can't do the action, the name is self-explanatory
     * @throws AlreadyBuiltException exception thrown if you can't do the action, the name is self-explanatory
     * @throws IllegalBuildingException exception thrown if you can't do the action, the name is self-explanatory
     * @throws IllegalMovementException exception thrown if you can't do the action, the name is self-explanatory
     * @throws AlreadyWalkedException exception thrown if you can't do the action, the name is self-explanatory
     * @throws endedMoveException exception thrown if you can't do the action, the name is self-explanatory
     * @throws OutOfOrderMoveException exception thrown if you can't do the action, the name is self-explanatory
     */
    public void doSpecialBuilding(String nickname, Point x) throws NotYourTurnException, endedMoveException, IllegalMovementException, IllegalBuildingException, OpponentEffectException, OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            match.getTurn().getCurrentPlayer().specialBuild(x);
        }
        else{
            throw new NotYourTurnException();
        }
    }

    /**
     * the player requests to end his turn
     * @param nickname the player nickname
     * @throws NotYourTurnException thrown if it is not the player's turn
     * @throws NotPossibleEndMoveException thrown if the player can not end his turn
     */
    public void doEndMove(String nickname) throws NotYourTurnException, NotPossibleEndMoveException {
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)) {
            match.getTurn().getCurrentPlayer().endMove();
        }
        else{
            throw new NotYourTurnException();
        }

    }

    /**
     * the player requires the points where he can move
     * @param nickname the player nickname
     * @return list of possible points
     * @throws NotYourTurnException thrown if it is not the player turn
     * @throws NoPossibleActionException thrown if the action is not possible
     * @throws AlreadyWalkedException thrown if the players has already walked
     */
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

    /**
     * the player requests points where he can build
     * @param nickname the player's nickname
     * @return the points where the player can build
     * @throws NotYourTurnException thrown if it is not the player turn
     * @throws NoPossibleActionException thrown if the action is not possible
     * @throws OutOfOrderMoveException thrown if the action can not be done in this order
     * @throws AlreadyBuiltException thrown if you can't build because the player has already done it
     * @throws AlreadyWalkedException thrown if you can't build because the player has already walked
     */
    public List<Point> getConstructionActions(String nickname) throws NotYourTurnException, NoPossibleActionException, OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException {
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

    /**
     * the player requests points where he can use the special building
     * @param nickname the player's nickname
     * @return the points where the player can build
     * @throws NotYourTurnException thrown if it is not the player turn
     * @throws NoPossibleActionException thrown if the action is not possible
     * @throws OutOfOrderMoveException thrown if the action can not be done in this order
     * @throws AlreadyBuiltException thrown if you can't build because the player has already done it
     * @throws AlreadyWalkedException thrown if you can't build because the player has already walked
     */
    public List<Point> getSpecialConstructionActions(String nickname) throws NotYourTurnException, NoPossibleActionException, OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException {
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
