package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.BasicWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.AlreadyChosenStarter;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidCardException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidNamePlayerException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoChallengerException;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
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
    private Match match;
    private boolean challenger;


    public Player(String nickname, Match match) {
        this.nickname = nickname;
        workers = new ArrayList<Worker>();
        this.match= match;
        this.challenger= false;
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
        Worker worker= new Worker(point, this,colour);
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
        return winCondition.hasWon(board, workers);
    }


    public Card getCard() {
        return card;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
