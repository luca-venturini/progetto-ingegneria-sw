package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.HephaestusMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * HephaestusCard Power -
 * Your Build: Your Worker may build one additional block (not dome) on top of your first block.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class HephaestusCard extends Card {

    /**
     * initialize the card
     */
    public HephaestusCard() {
        super();
        this.setName("Hephaestus");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new HephaestusMove();
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
