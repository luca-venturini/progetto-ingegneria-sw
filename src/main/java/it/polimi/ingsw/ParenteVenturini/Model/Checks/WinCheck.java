package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public interface WinCheck {
    /**
     * check if a player has won
     * @param board the used board
     * @param worker the actual worker
     * @param players list of all of the players
     * @return true if the player has won
     */
    boolean hasWon(Board board, Worker worker, List<Player> players);

    /**
     * check if a player has won during the turn of another player
     * @param board the used board
     * @return true if the player has won
     */
    boolean outOfTurnWon(Board board);
}
