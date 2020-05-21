package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalPlaceWorkerException;

import java.util.ArrayList;
import java.util.List;

/**
 * this class handle the setup phase in which each player place two workers
 */
public class PlaceWorkerSetupHandler {

    /** list of players */
    private List<Player> players;
    /** current player */
    private Player currentPlayer;
    /** the board */
    private Board board;
    /** number of workers added */
    private int numberOfAdd;

    /**
     * init the class
     * @param players list of players
     * @param board the game board
     */
    public PlaceWorkerSetupHandler(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
        currentPlayer = players.get(0);
        numberOfAdd = 0;
    }

    /**
     * discover if this phase has finished
     * @return true if this setup phase has finished
     */
    public boolean hasFinished() {
        return currentPlayer == null;
    }

    /**
     * get the current player
     * @return thecurrent player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * set the worker in a point chosen by the player
     * @param mPlayer the player
     * @param mPoint the selected point
     * @throws IllegalPlaceWorkerException thrown if the player can't place the worker in the selected point
     */
    public void setWorkerPosition(Player mPlayer, Point mPoint) throws IllegalPlaceWorkerException {
        if(mPoint.getX()<0 || mPoint.getX()>4 || mPoint.getY()<0 || mPoint.getY()>4 )
            throw new IllegalPlaceWorkerException();
        if(!board.isOccupied(mPoint) && mPlayer.getWorkers().size()<2 && mPlayer.equals(currentPlayer)) {
            int color = 1;
            if (numberOfAdd == 0 || numberOfAdd == 1)
                color = 1;
            if (numberOfAdd == 2 || numberOfAdd == 3)
                color = 2;
            if (numberOfAdd == 4 || numberOfAdd == 5)
                color = 3;
            mPlayer.placeWorker(color, mPoint, board);
            numberOfAdd++;
            updateCurrentPlayer();
            /*
            for(Worker w: mPlayer.getWorkers()){
                System.out.println("il nome è: "+ w.getPlayer().getNickname());
            }

             */
        }
        else
            throw new IllegalPlaceWorkerException();
    }

    /**
     * updete the player that has to place the workers
     */
    private void updateCurrentPlayer(){
        if (numberOfAdd == 2)
            currentPlayer = players.get(1);
        if(numberOfAdd == 4 && players.size()>2)
            currentPlayer = players.get(2);
        if(numberOfAdd == 4 && players.size()==2)
            currentPlayer = null;
        if (numberOfAdd == 6)
            currentPlayer = null;
    }

    /**
     * get a list of possible points where to place the workers
     * @return list of possible points
     */
    public List<Point> getPossiblePoint(){
        List<Point> possiblePoints = new ArrayList<>();

        for(int i = 0; i<5; i++){
            for(int j=0; j<5; j++){
                if(!board.isOccupied(i, j)){
                    possiblePoints.add(new Point(i, j));
                }
            }
        }

        return possiblePoints;
    }
}
