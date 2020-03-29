package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import java.util.List;

public class Deck {
    private List<Card> cards;
    private static Deck instance;

    private Deck(){
        Card card;
        // TODO: 29/03/2020 create deck
    }

    public static Deck createDeck(){
        if (instance == null) instance = new Deck();
        return instance;
    }
}
