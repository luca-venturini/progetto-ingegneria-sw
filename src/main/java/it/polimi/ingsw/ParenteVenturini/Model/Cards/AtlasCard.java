package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.AtlasMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

/**
 * {@inheritDoc}
 */
public class AtlasCard extends Card {

    /**
     * init the card
     */
    public AtlasCard() {
        super();
        this.setName("Atlas");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Move getMove() {
        return new AtlasMove();
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
