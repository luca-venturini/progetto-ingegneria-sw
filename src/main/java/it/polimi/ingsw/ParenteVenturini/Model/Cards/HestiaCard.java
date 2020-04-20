package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.HestiaMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class HestiaCard extends Card{

    public HestiaCard() {
        super();
        this.setName("Hestia");
    }

    @Override
    public Move getMove() {
        return new HestiaMove();
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
