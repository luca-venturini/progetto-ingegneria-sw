package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

public interface WinCheck {
    boolean hasWon(Board board, Worker worker);
}
