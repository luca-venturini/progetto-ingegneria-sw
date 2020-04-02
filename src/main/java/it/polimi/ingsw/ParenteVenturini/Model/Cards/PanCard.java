package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.PanWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.PanMove;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffectContainer;

public class PanCard extends Card {

    public PanCard(String name, String description) {
        super(name, description);
    }

    @Override
    public Move getMove() {
        return new PanMove();
    }

    @Override
    public WinCheck getWinCheck() {
        return new PanWinCheck();
    }

    @Override
    public OpponentEffect getOpponentEffect() {
        return null;
    }
}
