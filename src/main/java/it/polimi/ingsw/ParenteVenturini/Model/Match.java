package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Match {
    private List<Player> players;
    private List<Card> chosenCards;
    private Board board;
    private Deck deck;
    private Player starter;
    private int typeOfMatch;
    private OpponentEffectContainer opponentEffectContainer;
    private Player currentPlayer;
    private Turn turn;
    private Player challenger;

    public Match(){
        this.board= new Board();
        this.deck= new Deck();
        this.players= new ArrayList<Player>();
        this.chosenCards= new ArrayList<Card>();
        this.opponentEffectContainer = new OpponentEffectContainer();
        this.typeOfMatch = 2;
        this.challenger=null;
    }

    public void addPlayer(String nickname) throws NoMorePlayersException {
        if(getNumPlayers() < getTypeOfMatch()) {
            Player p = new Player(nickname,this);
            players.add(p);
        }
        else throw new NoMorePlayersException();
    }

    public boolean gameOver(){
        Move move = currentPlayer.callMove();
        Worker currentWorker = turn.getCurrentWorker();
        boolean result = false;
        if(move.forcedMovement(board, turn.getCurrentWorker())){
            result = gameOverMovement(move, board, currentWorker);
        }
        else if(move.forcedBuilding(board, turn.getCurrentWorker())){
            result = gameOverBuilding(move, board, currentWorker);
        }
        else if( !move.forcedMovement(board, turn.getCurrentWorker()) && !move.forcedBuilding(board, turn.getCurrentWorker()))
            return (gameOverMovement(move, board, currentWorker) || gameOverBuilding(move, board, currentWorker));
        return result;
    }

    private boolean gameOverMovement(Move move, Board board, Worker currentWorker){
        List<Point> points = move.possibleMovements(board, currentWorker);
        points = opponentEffectContainer.removeMovementPoint(points, currentWorker.getPosition(), currentWorker.getEffect(), board);
        return points == null;
    }

    private boolean gameOverBuilding(Move move, Board board, Worker currentWorker){
        List<Point> points = move.possibleBuildings(board, currentWorker);
        points = opponentEffectContainer.removeConstructionPoint(points, currentWorker.getPosition(), currentWorker.getEffect(), board);
        return points == null;
    }

    public Board getBoard() {
        return board;
    }

    public OpponentEffectContainer getOpponentEffectContainer() {
        return opponentEffectContainer;
    }

    public int getNumPlayers(){
        return players.size();
    }

    public int getTypeOfMatch() {
        return typeOfMatch;
    }

    public void setTypeOfMatch(int typeOfMatch) {
        this.typeOfMatch = typeOfMatch;
    }

    public void setChallenger() {
        Random rand= new Random();
        this.challenger=players.get(rand.nextInt(players.size()));
    }

    public Player selectPlayer(String name){
        for(Player p: players){
            if(name.equals(p.getNickname()) ){
                return p;
            }
        }
        return null;
    }

    public void selectCardFromDeck(String name) throws InvalidCardException, NoMoreCardsException {
        Card c = this.deck.selectByName(name);
        if (chosenCards.size() < getTypeOfMatch()){
            if (c != null && !chosenCards.contains(c)) {
                chosenCards.add(c);
            } else throw new InvalidCardException();
        }else throw  new NoMoreCardsException();
    }

    public void selectStarter(String nickname) throws AlreadyChosenStarter, InvalidNamePlayerException {
        if(starter == null){
            Player p= selectPlayer(nickname);
            if(p != null) {
                this.starter = p;
                orderPlayers();
            }
            else throw new InvalidNamePlayerException();
        }
        else throw new AlreadyChosenStarter();

    }

    public List<Card> getChosenCards(){
        return chosenCards;
    }

    public void orderPlayers(){
        List<Player> p= new ArrayList<Player>();
        p.add(this.starter);
        for(Player i:players){
            if(! i.equals(starter)){
                p.add(i);
            }
        }
        this.players=p;
    }

    public List<Player> getPlayers(){
        return players;
    }

    public void setChosenCards(List<Card> cards){
        chosenCards = cards;
    }

    public Player getChallenger() {
        return challenger;
    }

}
