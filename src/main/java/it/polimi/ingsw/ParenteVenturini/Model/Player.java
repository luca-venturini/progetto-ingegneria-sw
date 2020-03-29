package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Card card;
    private List<Worker> workers;
    private String nickname;

    public Player(String nickname) {
        this.nickname = nickname;
        workers = new ArrayList<Worker>();
    }

    public void setCard(Card card){
        this.card = card;
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

    public Card getCard() {
        return card;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
