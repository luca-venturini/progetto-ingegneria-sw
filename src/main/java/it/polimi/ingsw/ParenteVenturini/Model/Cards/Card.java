package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.OpponentEffectContainer;

public abstract class Card {
    private String name;

    protected void setName(String name){
        this.name= name;
    }

    public abstract Move getMove();

    public String getName() {
        return name;
    }

    public abstract WinCheck getWinCheck();

    public abstract OpponentEffect getOpponentEffect();
}
