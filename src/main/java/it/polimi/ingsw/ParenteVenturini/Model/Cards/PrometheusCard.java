package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PrometheusMove;

/**
 * {@inheritDoc}
 */
public class PrometheusCard extends Card {
    /**
     * init the card
     */
    public PrometheusCard() {
        super();
        this.setName("Prometheus");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new PrometheusMove();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WinCheck getWinCheck() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpponentEffect getOpponentEffect() {
        return null;
    }
}
