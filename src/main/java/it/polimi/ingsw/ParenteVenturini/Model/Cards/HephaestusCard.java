package it.polimi.ingsw.ParenteVenturini.Model.Cards;

import it.polimi.ingsw.ParenteVenturini.Model.Moves.HephaestusMove;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

public class HephaestusCard extends Card {
    public HephaestusCard(String name, String description) {
        super(name, description);
    }

    @Override
    public Move getMove() {
        return new HephaestusMove();
    }
}
