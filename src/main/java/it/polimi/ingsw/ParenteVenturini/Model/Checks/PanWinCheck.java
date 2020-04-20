package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class PanWinCheck extends BasicWinCheck {

    @Override
    public boolean hasWon(Board board, Worker worker, List<Player> players) {
        if (super.hasWon(board, worker, players) )
            return true;
        if( board.blockLevel(worker.getLastPosition()) -  board.blockLevel(worker.getPosition()) >=2 )
            return true;
        return false;
    }
}
