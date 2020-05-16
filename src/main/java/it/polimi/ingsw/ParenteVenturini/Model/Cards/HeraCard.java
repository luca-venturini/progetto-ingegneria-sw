package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.HeraEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.HeraMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * {@inheritDoc}
 */
public class HeraCard extends Card {
    /**
     * init the card
     */
    public HeraCard() {
        super();
        this.setName("Hera");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new HeraMove();
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
        return new HeraEffect();
    }
}
