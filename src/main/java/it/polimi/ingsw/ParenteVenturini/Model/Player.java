package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Card.Card;

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

    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public Card getCard() {
        return card;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
