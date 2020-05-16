package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.TritonMove;

/**
 * {@inheritDoc}
 */
public class TritonCard extends Card {

    /**
     * init the card
     */
    public TritonCard() {
        super();
        this.setName("Triton");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new TritonMove();
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
