package it.polimi.ingsw.ParenteVenturini.Model.Card;

import it.polimi.ingsw.ParenteVenturini.Model.Effect;

public abstract class OpponentTurnCard extends Card {
    public OpponentTurnCard(String name, String description) {
        super(name, description);
    }

    public abstract Effect getEffect();
}
