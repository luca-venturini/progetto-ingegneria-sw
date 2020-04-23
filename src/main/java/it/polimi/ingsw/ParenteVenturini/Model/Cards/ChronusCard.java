package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.ChronusWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.PanWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ChronusMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PanMove;

public class ChronusCard extends Card{
    public ChronusCard() {
        super();
        this.setName("Chronus");
    }

    @Override
    public Move getMove() {
        return new ChronusMove();
    }

    @Override
    public WinCheck getWinCheck() {
        return new ChronusWinCheck();
    }

    @Override
    public OpponentEffect getOpponentEffect() {
        return null;
    }
}
