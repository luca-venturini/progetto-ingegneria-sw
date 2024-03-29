package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.ChronusWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.PanWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ChronusMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PanMove;

/**
 * ChronusCard Power -
 * Win Condition: You also win when there are at least five Complete Towers on the board.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class ChronusCard extends Card{
    /**
     * initialize the card
     */
    public ChronusCard() {
        super();
        this.setName("Chronus");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new ChronusMove();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WinCheck getWinCheck() {
        return new ChronusWinCheck();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpponentEffect getOpponentEffect() {
        return null;
    }
}
