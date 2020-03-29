package it.polimi.ingsw.ParenteVenturini.Model.Card;

public abstract class Card {
    private String name;
    private String description;

    public Card(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract boolean isConditionValid();
/*
    public abstract Action getAction();

    public abstract WinCheck getWinCondition();

    public abstract Effect getEffect();
 */

}
