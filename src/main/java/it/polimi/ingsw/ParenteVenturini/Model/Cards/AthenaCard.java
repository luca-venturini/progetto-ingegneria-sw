package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.BasicWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.AthenaEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.AthenaMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class AthenaCard extends Card {

    public AthenaCard(String name, String description) {
        super(name, description);
    }

    @Override
    public Move getMove() {
        return new AthenaMove();
    }

    @Override
    public WinCheck getWinCheck() {
        return null;
    }

    @Override
    public OpponentEffect getOpponentEffect() {
        return new AthenaEffect();
    }
}
