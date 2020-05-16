package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.HestiaMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * {@inheritDoc}
 */
public class HestiaCard extends Card{

    /**
     * init the card
     */
    public HestiaCard() {
        super();
        this.setName("Hestia");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new HestiaMove();
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
