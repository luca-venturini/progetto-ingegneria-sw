package it.polimi.ingsw.ParenteVenturini.Model.Card;

import it.polimi.ingsw.ParenteVenturini.Model.Actions.WinCheck;

public abstract class WinConditionCard extends Card {

    public WinConditionCard(String name, String description) {
        super(name, description);
    }

    public abstract WinCheck getWinCondition();
}
