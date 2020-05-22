package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.TritonMove;

/**
 * TritonCard Power -
 * Your Move: Each time your Worker moves into a perimeter space, it may immediately move again.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class TritonCard extends Card {

    /**
     * initialize the card
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
