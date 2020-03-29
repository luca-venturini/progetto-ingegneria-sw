package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

public class BasicWinCheck implements WinCheck {

    @Override
    public boolean hasWon(Board board, Worker worker) {
        return board.blockLevel(worker.getPosition()) == 3;
    }
}
