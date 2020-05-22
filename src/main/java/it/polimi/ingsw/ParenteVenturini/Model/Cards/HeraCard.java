package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.HeraEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.HeraMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * HeraCard Power -
 * Opponent's Turn: An opponent cannot win by moving into a perimeter space.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class HeraCard extends Card {
    /**
     * initialize the card
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
