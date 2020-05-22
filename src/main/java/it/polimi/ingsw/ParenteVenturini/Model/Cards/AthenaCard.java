package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.AthenaEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.AthenaMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * AthenaCard Power -
 * Opponent's Turn: If one of your Workers moved up on your last turn, opponent Workers cannot move up this turn.
 *
 *  It returns Move class, WinCheck class and OpponentEffect class
 */
public class AthenaCard extends Card {

    /**
     * initialize the card
     */
    public AthenaCard() {
        super();
        this.setName("Athena");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new AthenaMove();
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
        return new AthenaEffect();
    }
}
