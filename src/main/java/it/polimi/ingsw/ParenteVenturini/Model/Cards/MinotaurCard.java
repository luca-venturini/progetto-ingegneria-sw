package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.MinotaurMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * MinotaurCard Power -
 * Your Move: Your Worker may move into an opponent Worker's space, if their Worker can be forced one space straight backwards to an unoccupied space at any level.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class MinotaurCard extends Card {
    /**
     * initialize the card
     */
    public MinotaurCard() {
        super();
        this.setName("Minotaur");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new MinotaurMove();
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
