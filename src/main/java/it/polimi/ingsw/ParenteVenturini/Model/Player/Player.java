package it.polimi.ingsw.ParenteVenturini.Model.Player;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.BasicWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Card card;
    private List<Worker> workers;
    private String nickname;
    private WinCheck winCondition;

    public Player(String nickname) {
        this.nickname = nickname;
        workers = new ArrayList<Worker>();
    }

    public void setCard(Card card){
        this.card = card;
        setWinCondition();
    }

    private void setWinCondition(){
        if(card == null || card.getWinCheck() == null)
            this.winCondition = new BasicWinCheck();
        else
            this.winCondition = card.getWinCheck();
    }

    public void placeWorker(int colour , Point point, Board board){
        Worker worker= new Worker(point,this,colour);
        addWorker(worker);
        board.setWorker(worker);
    }
    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public Worker selectWorker(int num){
        return workers.get(num);
    }

    public void callMove(int num){
        Worker w = selectWorker(num);
        Move myMove= card.getMove();
    }

    public boolean hasWon(Board board){
        if (winCondition.hasWon(board, workers)) {
            return true;
        }
        return false;
    }


    public Card getCard() {
        return card;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
