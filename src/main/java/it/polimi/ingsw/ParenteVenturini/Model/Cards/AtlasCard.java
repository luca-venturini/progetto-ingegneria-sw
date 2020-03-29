package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Moves.AtlasMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class AtlasCard extends Card {

    public AtlasCard(String name, String description) {
        super(name, description);
    }

    @Override
    public Move getMove() {
        return new AtlasMove();
    }

}
