package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ZeusMove;

/**
 * {@inheritDoc}
 */
public class ZeusCard extends Card {

    /**
     * init the card
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
