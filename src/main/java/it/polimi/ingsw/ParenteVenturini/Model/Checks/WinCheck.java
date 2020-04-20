package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public interface WinCheck {
    boolean hasWon(Board board, Worker worker, List<Player> players);
}
