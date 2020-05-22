package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ApolloMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * ApolloCard Power -
 * Your Move: Your Worker may move into an opponent Worker's space by forcing their Worker to the space yours just vacated.
 *
 *  It returns Move class, WinCheck class and OpponentEffect class
 */
public class ApolloCard extends Card {

    /**
     * initialize the card
     */
    public ApolloCard() {
        super();
        this.setName("Apollo");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new ApolloMove();
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
