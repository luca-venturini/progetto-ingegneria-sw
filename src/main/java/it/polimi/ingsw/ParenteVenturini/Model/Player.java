package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.AlreadyChosenStarter;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidCardException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidNamePlayerException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoChallengerException;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Card card;
    private List<Worker> workers;
    private String nickname;
    private Match match;
    private boolean challenger;

    public Player(String nickname, Match match) {
        this.nickname = nickname;
        this.match= match;
        this.challenger= false;
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

    public boolean isChallenger() {
        return challenger;
    }

    public void setChallenger(boolean challenger) {
        this.challenger = challenger;
    }

    public void chooseCard(String nameCard) throws NoChallengerException, InvalidCardException {
        if(challenger) {
            match.chooseCard(nameCard);
        }else throw new NoChallengerException();
    }

    public void chooseStarter(String nickname) throws NoChallengerException, AlreadyChosenStarter, InvalidNamePlayerException {
        if(challenger) {
            match.chooseStarter(nickname);
        }else throw new NoChallengerException();
    }

    public String getNickname() {
        return nickname;
    }
}
