package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.PanWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PanMove;

public class PanCard extends Card {

    public PanCard() {
        super();
        this.setName("Pan");
    }

    @Override
    public Move getMove() {
        return new PanMove();
    }

    @Override
    public WinCheck getWinCheck() {
        return new PanWinCheck();
    }

    @Override
    public OpponentEffect getOpponentEffect() {
        return null;
    }
}
