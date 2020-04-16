package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.DemeterMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class DemeterCard extends Card {

    public DemeterCard() {
        super();
        this.setName("Demeter");
    }

    @Override
    public Move getMove() {
        return new DemeterMove();
    }

    @Override
    public WinCheck getWinCheck() {
        return null;
    }

    @Override
    public OpponentEffect getOpponentEffect() {
        return null;
    }
}
