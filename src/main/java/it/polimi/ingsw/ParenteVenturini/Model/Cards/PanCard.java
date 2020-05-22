package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.PanWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PanMove;

/**
 * PanCard Power -
 * Win Condition: You also win if your Worker moves down two or more levels.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class PanCard extends Card {

    /**
     * initialize the card
     */
    public PanCard() {
        super();
        this.setName("Pan");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new PanMove();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WinCheck getWinCheck() {
        return new PanWinCheck();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OpponentEffect getOpponentEffect() {
        return null;
    }
}
