package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.AtlasMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class AtlasCard extends Card {

    public AtlasCard() {
        super();
        this.setName("Atlas");
    }

    @Override
    public Move getMove() {
        return new AtlasMove();
    }

    @Override
    public WinCheck getWinCheck() {
        return null;
    }

    @Override
    public OpponentEffect getOpponentEffect() {
        return null;
    }

}
