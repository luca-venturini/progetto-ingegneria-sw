package it.polimi.ingsw.ParenteVenturini.Model;

import java.util.List;

public class Turn {

    private List<Player> players;
    private OpponentEffectContainer opponentEffectContainer;
    private Player currentPlayer;
    private Worker currentWorker;
    private int numTurn;

    public Turn(List<Player> players) {
        this.players = players;
        this.currentPlayer = players.get(0);
        this.numTurn=1;
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
    }

    private void updateNewTurn(){
        numTurn++;
        opponentEffectContainer.switchToNewTurn();
    }

    public void setActualWorker(Worker w){
        if(currentWorker == null)
            currentWorker = w;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Worker getCurrentWorker() {
        return currentWorker;
    }
}
