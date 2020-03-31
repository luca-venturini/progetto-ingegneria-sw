package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffect;

public class PanCard extends Card {

    public PanCard(String name, String description) {
        super(name, description);
    }

    @Override
    public Move getMove() {
        return null;
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
