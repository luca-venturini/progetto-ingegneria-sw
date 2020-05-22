package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.HestiaMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * HestiaCard Power -
 * Your Build: Your Worker may build one additional time, but this cannot be on a perimeter space.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class HestiaCard extends Card{

    /**
     * initialize the card
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
