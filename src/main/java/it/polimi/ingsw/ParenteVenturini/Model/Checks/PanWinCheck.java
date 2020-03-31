package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.HashMap;
import java.util.List;

public class PanWinCheck implements WinCheck {

    List<Integer> maxLevelWorkers;

    @Override
    public boolean hasWon(Board board, List<Worker> workers) {
        for(Worker w: workers) {
            if (board.blockLevel(w.getPosition()) == 3)
                return true;
        }

        int numWorker = 0;
        for(Worker w: workers){
            if(maxLevelWorkers.get(numWorker) <  board.blockLevel(w.getPosition()))
                maxLevelWorkers.add(numWorker, board.blockLevel(w.getPosition()));
            else if(maxLevelWorkers.get(numWorker)-board.blockLevel(w.getPosition())>=2){
                return true;
            }
            numWorker++;
        }
        return false;

    }
}
