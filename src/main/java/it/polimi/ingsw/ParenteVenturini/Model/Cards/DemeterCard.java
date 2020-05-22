package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.DemeterMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * DemeterCard Power -
 * Your Build: Your Worker may build one additional time, but not on the same space.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class DemeterCard extends Card {

    /**
     * initialize the card
     */
    public DemeterCard() {
        super();
        this.setName("Demeter");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new DemeterMove();
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
