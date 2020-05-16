package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.ApolloMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * {@inheritDoc}
 */
public class ApolloCard extends Card {

    /**
     * init the card
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
