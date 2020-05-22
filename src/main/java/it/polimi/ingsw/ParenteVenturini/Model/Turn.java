package it.polimi.ingsw.ParenteVenturini.Model;

import java.util.List;

/**
 * class that handle the turn during the match
 */
public class Turn {

    /** list of players */
    private List<Player> players;
    /** reference to the opponent effect container*/
    private OpponentEffectContainer opponentEffectContainer;
    /** the current player */
    private Player currentPlayer;
    /** the current worker */
    private Worker currentWorker;
    /** turn's number */
    private int numTurn;

    /**
     * initialize the class
     * @param players the players of the match
     * @param op reference to the opponentEffectContainer
     */
    public Turn(List<Player> players, OpponentEffectContainer op) {
        this.players = players;
        this.currentPlayer = players.get(0);
        this.numTurn=1;
        this.opponentEffectContainer=op;
        currentPlayer.setMove(currentPlayer.callMove());
    }

    /**
     * change the current player
     */
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

    /**
     * update to new turn
     */
    private void updateNewTurn(){
        numTurn++;
        opponentEffectContainer.switchToNewTurn();
    }

    /**
     * set the worker used during the turn
     * @param w the chosen worker
     */
    public void setActualWorker(Worker w){
        currentWorker = w;
    }

    /**
     * get the current player
     * @return the player that must play at that moment
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * get the current worker
     * @return the chosen worker
     */
    public Worker getCurrentWorker() {
        return currentWorker;
    }

    /**
     * get the number of the current turn
     * @return the number of the turn
     */

    public int getNumTurn() {
        return numTurn;
    }
}
