package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class BasicWinCheck implements WinCheck {

    @Override
    public boolean hasWon(Board board, List<Worker> workers) {
        for(Worker w: workers) {
            if (board.blockLevel(w.getPosition()) == 3 && board.blockLevel(w.getLastPosition()) != 3)
                return true;
        }
        return false;

    }
}
