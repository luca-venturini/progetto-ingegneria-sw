package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

/**
 * check if the player wins thanks to basic or Chronus win rules
 */
public class ChronusWinCheck extends BasicWinCheck{

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasWon(Board board, Worker worker, List<Player> players) {
        return super.hasWon(board, worker, players);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean outOfTurnWon(Board board) {
        int sum = 0;
        for(Block b : board.getBlocks()){
            if(b.getLevel() > 3 && b.isDome())
                sum += 1;
        }
        return sum > 4;
    }
}
