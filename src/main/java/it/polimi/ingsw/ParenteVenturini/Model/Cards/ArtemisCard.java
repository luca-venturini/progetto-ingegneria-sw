package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ArtemisMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class ArtemisCard extends Card {

    public ArtemisCard() {
        super();
        this.setName("Artemis");
    }

    @Override
    public Move getMove() {
        return new ArtemisMove();
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
