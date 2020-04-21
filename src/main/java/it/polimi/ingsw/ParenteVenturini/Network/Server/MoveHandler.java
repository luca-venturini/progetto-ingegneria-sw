package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NotPossibleEndMoveException;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalCardException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;

import java.util.List;

public class MoveHandler {
    private Match match;


    public MoveHandler(Match match) {
        this.match=match;
    }

    public void doMovement() throws NotYourTurnException {
        if(match.getTurn().getCurrentPlayer().equals(nickname)) {

        }
        else{
            throw new NotYourTurnException();
        }

    }

    public void doBuilding(){

    }

    public void doEndMove(String nickname;) throws NotYourTurnException, NotPossibleEndMoveException {
        if(match.getTurn().getCurrentPlayer().equals(nickname)) {
            match.selectPlayer(nickname).endMove();
        }
        else{
            throw new NotYourTurnException();
        }

    }

    public void getMovementsActions(String nickname) throws NotYourTurnException {
        if(match.getTurn().getCurrentPlayer().equals(nickname)) {

        }
        else{
            throw new NotYourTurnException();
        }

    }

    }

}
