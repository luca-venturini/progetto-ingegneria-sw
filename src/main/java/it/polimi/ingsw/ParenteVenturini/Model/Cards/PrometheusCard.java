package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PrometheusMove;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffect;

public class PrometheusCard extends Card {
    public PrometheusCard(String name, String description) {
        super(name, description);
    }

    @Override
    public Move getMove() {
        return new PrometheusMove();
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
