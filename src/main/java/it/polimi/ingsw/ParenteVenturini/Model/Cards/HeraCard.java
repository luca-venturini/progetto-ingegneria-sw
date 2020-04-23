package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.HeraEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.HeraMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class HeraCard extends Card {
    public HeraCard() {
        super();
        this.setName("Hera");
    }

    @Override
    public Move getMove() {
        return new HeraMove();
    }

    @Override
    public WinCheck getWinCheck() {
        return null;
    }

    @Override
    public OpponentEffect getOpponentEffect() {
        return new HeraEffect();
    }
}
