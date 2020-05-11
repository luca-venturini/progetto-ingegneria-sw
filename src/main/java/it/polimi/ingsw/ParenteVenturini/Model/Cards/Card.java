package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffectContainer;

/**
 * this class represent the cards, it returns Move class, WinCheck class and OpponentEffect based on the specific card
 */
public abstract class Card {
    private String name;

    /**
     * set the name of the card
     * @param name the card's name
     */
    protected void setName(String name){
        this.name= name;
    }

    /**
     * return a new Move
     * @return the move based on the specific class
     */
    public abstract Move getMove();

    /**
     * gat the name card
     * @return the card's name
     */
    public String getName() {
        return name;
    }

    /**
     * return a new WinCheck
     * @return the WinCheck based on the specific class
     */

    public abstract WinCheck getWinCheck();

    /**
     * return a new OpponentEffect
     * @return the OpponentEffect based on the specific class
     */
    public abstract OpponentEffect getOpponentEffect();
}
