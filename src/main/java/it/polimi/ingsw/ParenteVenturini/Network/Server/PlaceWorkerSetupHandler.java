package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalPlaceWorkerException;

import java.util.ArrayList;
import java.util.List;

public class PlaceWorkerSetupHandler {

    private List<Player> players;
    private Player currentPlayer;
    private Board board;
    private int numberOfAdd;

    public PlaceWorkerSetupHandler(List<Player> players, Board board) {
        this.players = players;
        this.board = board;
        currentPlayer = players.get(0);
        numberOfAdd = 0;
    }

    public boolean hasFinished() {
        return currentPlayer == null;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

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
            for(Worker w: mPlayer.getWorkers()){
                System.out.println("il nome è: "+ w.getPlayer().getNickname());
            }
        }
        else
            throw new IllegalPlaceWorkerException();
    }

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
