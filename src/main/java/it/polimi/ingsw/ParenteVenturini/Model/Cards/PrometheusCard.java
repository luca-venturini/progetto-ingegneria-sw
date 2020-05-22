package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PrometheusMove;

/**
 * PrometheusCard Power -
 * Your Turn: If your Worker does not move up, it may build both before and after moving.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class PrometheusCard extends Card {
    /**
     * initialize the card
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
