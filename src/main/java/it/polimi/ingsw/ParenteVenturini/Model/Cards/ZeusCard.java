package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ZeusMove;

/**
 * ZeusCard Power -
 * Your Build: Your Worker may build a block under itself.
 *
 * It returns Move class, WinCheck class and OpponentEffect class
 */
public class ZeusCard extends Card {

    /**
     * initialize the card
     */
    public ZeusCard() {
        super();
        this.setName("Zeus");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new ZeusMove();
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
