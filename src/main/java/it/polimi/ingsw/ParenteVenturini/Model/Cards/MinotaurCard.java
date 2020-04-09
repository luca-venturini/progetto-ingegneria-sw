package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.MinotaurMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class MinotaurCard extends Card {
    public MinotaurCard(String name, String description) {
        super(name, description);
    }

    @Override
    public Move getMove() {
        return new MinotaurMove();
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
