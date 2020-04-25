package it.polimi.ingsw.ParenteVenturini.Model.Checks;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;


import java.util.List;

public class BasicWinCheck implements WinCheck {

    @Override
    public boolean hasWon(Board board, Worker worker, List<Player> players) {
        for(Player p: players){
            OpponentEffect eff = p.getCard().getOpponentEffect();
            if (eff != null && eff.isWinEffect() && !eff.equals(worker.getEffect())){
                if(!eff.isWinner(board, worker))
                    return false;
            }
        }
        if (board.blockLevel(worker.getPosition()) == 3 && board.blockLevel(worker.getLastPosition()) != 3)
            return true;
        return false;

    }

    @Override
    public boolean outOfTurnWon(Board board) {
        return false;
    }
}
