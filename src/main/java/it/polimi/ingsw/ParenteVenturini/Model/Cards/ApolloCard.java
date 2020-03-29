package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Moves.ApolloMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class ApolloCard extends Card {

    public ApolloCard(String name, String description) {
        super(name, description);
    }

    @Override
    public Move getMove() {
        return new ApolloMove();
    }

}
