package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.BasicWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidCardException;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Card card;
    private List<Worker> workers;
    private String nickname;
    private WinCheck winCondition;
    private Match match;


    public Player(String nickname, Match match) {
        this.nickname = nickname;
        workers = new ArrayList<Worker>();
        this.match= match;
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
    private void addWorker(Worker worker){
        workers.add(worker);
    }

    public Worker selectWorker(int num){
        return workers.get(num);
    }

    public Move callMove(){
        return card.getMove();
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

    public void chooseCard(String nameCard) throws InvalidCardException {
        List<Card> cards=match.getChosenCards();
        for(Card c:cards) {
            if ( nameCard.equals(c.getName()) ) {
                this.card=c;
            }
            else throw new InvalidCardException();
        }
    }

    public String getNickname() {
        return nickname;
    }

    public OpponentEffect getOpponentEffectPlayer(){
        return card.getOpponentEffect();
    }

}
