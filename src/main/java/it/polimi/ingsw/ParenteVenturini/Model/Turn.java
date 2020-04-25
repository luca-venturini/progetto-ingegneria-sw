package it.polimi.ingsw.ParenteVenturini.Model;

import java.util.List;

public class Turn {

    private List<Player> players;
    private OpponentEffectContainer opponentEffectContainer;
    private Player currentPlayer;
    private Worker currentWorker;
    private int numTurn;

    public Turn(List<Player> players, OpponentEffectContainer op) {
        this.players = players;
        this.currentPlayer = players.get(0);
        this.numTurn=1;
        this.opponentEffectContainer=op;
        currentPlayer.setMove(currentPlayer.callMove());
    }

    public void setNextPlayer() {
        int lastPlayer = players.indexOf(currentPlayer);
        if(lastPlayer == players.size()-1 ) {
            currentPlayer = players.get(0);
            updateNewTurn();
        }
        else
            currentPlayer = players.get(lastPlayer+1);
        currentWorker = null;
        currentPlayer.setMove(currentPlayer.callMove());

    }

    private void updateNewTurn(){
        numTurn++;
        opponentEffectContainer.switchToNewTurn();
    }

    public void setActualWorker(Worker w){
        if(currentWorker == null)
            currentWorker = w;
    }

    public void cleanCurrentWorker(){
        currentWorker = null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Worker getCurrentWorker() {
        return currentWorker;
    }

    public int getNumTurn() {
        return numTurn;
    }
}
