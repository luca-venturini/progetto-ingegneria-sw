package it.polimi.ingsw.ParenteVenturini.Model.Card;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.Action;
import it.polimi.ingsw.ParenteVenturini.Model.Actions.ApolloMovement;

public class ApolloCard extends ActionCard {

    public ApolloCard(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isConditionValid() {
        return false;
    }

    @Override
    public Action getAction() {
        return new ApolloMovement();
    }

}
