package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ArtemisMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * ArtemisCard Power -
 * Your Move: Your Worker may move one additional time, but not back to its initial space.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class ArtemisCard extends Card {

    /**
     * initialize the card
     */
    public ArtemisCard() {
        super();
        this.setName("Artemis");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new ArtemisMove();
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
