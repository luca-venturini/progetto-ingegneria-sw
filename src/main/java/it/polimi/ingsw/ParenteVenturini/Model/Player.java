package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.BasicWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidCardException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.OpponentEffectException;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private Card card;
    private List<Worker> workers;
    private String nickname;
    private WinCheck winCondition;
    private Match match;
    private Move move;


    public Player(String nickname, Match match) {
        this.nickname = nickname;
        workers = new ArrayList<Worker>();
        this.match= match;
        this.move=null;
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
        Worker worker= new Worker(point, this, colour);
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
                setCard(c);
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

    public void walk(Point p, int n) throws OpponentEffectException {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = selectWorker(n);
        if(match.getOpponentEffectContainer().checkMovementPoint(p, myWorker,match.getBoard())){
            try {
                move.walk(p, match.getBoard(), myWorker);
                OpponentEffect temp= card.getOpponentEffect();
                if(temp!= null && temp.isMovementValid(p, myWorker.getPosition(), match.getBoard())){
                    match.getOpponentEffectContainer().addEffect(card.getOpponentEffect());
                }
                if(move.getHasEnded()){
                    endMove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            throw new OpponentEffectException();
    }

    public void build(Point p, int n) throws OpponentEffectException {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = selectWorker(n);
        if(match.getOpponentEffectContainer().checkConstructionPoint(p, myWorker, match.getBoard()) ){
            try {
                move.build(p, match.getBoard(), myWorker);
                OpponentEffect temp= card.getOpponentEffect();
                if(temp!= null && temp.isConstructionValid(p, myWorker.getPosition(), match.getBoard())){
                    match.getOpponentEffectContainer().addEffect(card.getOpponentEffect());
                }
                if(move.getHasEnded()){
                    endMove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            throw new OpponentEffectException();
    }

    public void specialBuild(Point p, int n) throws OpponentEffectException {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = selectWorker(n);
        if(match.getOpponentEffectContainer().checkConstructionPoint(p, myWorker, match.getBoard())){
            try {
                move.specialBuild(p, match.getBoard(), myWorker);
                OpponentEffect temp= card.getOpponentEffect();
                if(temp!= null && temp.isConstructionValid(p, myWorker.getPosition(), match.getBoard())){
                    match.getOpponentEffectContainer().addEffect(card.getOpponentEffect());
                }
                if(move.getHasEnded()){
                    endMove();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            throw new OpponentEffectException();
    }

    public List<Point> getPossibleMovements(int n) {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker =selectWorker(n);
        List<Point> temp = move.possibleMovements(match.getBoard(), myWorker);
        temp = match.getOpponentEffectContainer().removeMovementPoint(temp, myWorker.getPosition(), myWorker.getEffect(), match.getBoard());
        return temp;
    }

    public List<Point> getPossibleBuildings(int n) {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = selectWorker(n);
        List<Point> temp = move.possibleBuildings(match.getBoard(), myWorker);
        temp = match.getOpponentEffectContainer().removeConstructionPoint(temp, myWorker.getPosition(), myWorker.getEffect(), match.getBoard());
        return temp;
    }

    public void endMove(){
        if(this.move.getHasEnded() || this.move.getHasWalkedandBuilt() ){
            move = null;
            match.getTurn().setNextPlayer();
        }
    }

    public List<Worker> getWorkers(){
        return workers;
    }
}
