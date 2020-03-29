package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public abstract class Card {
    private String name;
    private String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract Move getMove();
}
