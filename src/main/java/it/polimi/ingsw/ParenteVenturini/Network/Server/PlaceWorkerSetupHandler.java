package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.List;

public class PlaceWorkerSetupHandler {

    private List<Player> players;
    private Player currentPlayer;
    private Board board;

    public PlaceWorkerSetupHandler(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
        currentPlayer = players.get(0);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setWorkerPosition(Player mPlayer, int numWorker, Point mPoint){
        Worker w = mPlayer.selectWorker(numWorker);
        if(!board.isOccupied(mPoint) && w.getPosition() == null)
            w.setPosition(mPoint);
        //else
            //throw new IllegalPlaceWorkerException();
    }
}
