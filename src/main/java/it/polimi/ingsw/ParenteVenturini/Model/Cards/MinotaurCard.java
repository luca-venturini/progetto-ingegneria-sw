package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.MinotaurMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * {@inheritDoc}
 */
public class MinotaurCard extends Card {
    /**
     * init the card
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
