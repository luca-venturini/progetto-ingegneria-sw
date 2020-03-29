package it.polimi.ingsw.ParenteVenturini.Model.Card;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;

public abstract class ActionCard extends Card {

    public ActionCard(String name, String description) {
        super(name, description);
    }

    public abstract Action getAction();
}
