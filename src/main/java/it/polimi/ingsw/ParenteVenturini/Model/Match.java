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

    public Match(){
        this.board= new Board();
        this.deck= new Deck();
        this.players= new ArrayList<Player>();
        this.chosenCards= new ArrayList<Card>();
        this.opponentEffectContainer = new OpponentEffectContainer();
        this.typeOfMatch = 2;
    }

    public void addPlayer(String nickname) throws NoMorePlayersException {
        if(getNumPlayers() < getTypeOfMatch()) {
            Player p = new Player(nickname,this);
            players.add(p);
        }
        else throw new NoMorePlayersException();
    }

    public void walk(Point p, int n) throws OpponentEffectException {
        Move move = currentPlayer.callMove();
        Worker myWorker = currentPlayer.selectWorker(n);
        if(opponentEffectContainer.checkMovementPoint(p, myWorker, board)){
            try {
                move.walk(p, board, myWorker);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            throw new OpponentEffectException();
    }

    public void build(Point p, int n) throws OpponentEffectException {
        Move move = currentPlayer.callMove();
        Worker myWorker = currentPlayer.selectWorker(n);
        if(opponentEffectContainer.checkConstructionPoint(p, myWorker, board)){
            try {
                move.build(p, board, myWorker);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            throw new OpponentEffectException();
    }

    public void specialBuild(Point p, int n) throws OpponentEffectException {
        Move move = currentPlayer.callMove();
        Worker myWorker = currentPlayer.selectWorker(n);
        if(opponentEffectContainer.checkConstructionPoint(p, myWorker, board)){
            try {
                move.specialBuild(p, board, myWorker);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            throw new OpponentEffectException();
    }

    public List<Point> getPossibleMovements(int n){
        Move move = currentPlayer.callMove();
        Worker myWorker = currentPlayer.selectWorker(n);
        List<Point> temp = move.possibleMovements(board, myWorker);
        temp = opponentEffectContainer.removeMovementPoint(temp, myWorker.getPosition(), myWorker.getEffect(), board);
        return temp;
    }

    public List<Point> getPossibleBuildings(int n){
        Move move = currentPlayer.callMove();
        Worker myWorker = currentPlayer.selectWorker(n);
        List<Point> temp = move.possibleBuildings(board, myWorker);
        temp = opponentEffectContainer.removeConstructionPoint(temp, myWorker.getPosition(), myWorker.getEffect(), board);
        return temp;
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
        if(points == null)
            return true;
        return false;
    }

    private boolean gameOverBuilding(Move move, Board board, Worker currentWorker){
        List<Point> points = move.possibleBuildings(board, currentWorker);
        points = opponentEffectContainer.removeConstructionPoint(points, currentWorker.getPosition(), currentWorker.getEffect(), board);
        if(points == null)
            return true;
        return false;
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
        Player challenger=players.get(rand.nextInt(players.size()));
        challenger.setChallenger(true);
    }

    public Player selectPlayer(String name){
        for(Player p: players){
            if(name.equals(p.getNickname()) ){
                return p;
            }
        }
        return null;
    }

    public void chooseCard(String name) throws InvalidCardException {
        Card c=this.deck.selectByName(name);
        if( c!=null && !chosenCards.contains(c) ){
            chosenCards.add(c);
        }
        else throw new InvalidCardException();

    }

    public void chooseStarter(String name) throws AlreadyChosenStarter, InvalidNamePlayerException {
        if(starter == null){
            Player p= selectPlayer(name);
            if(p != null)
                this.starter=p;
            else throw new InvalidNamePlayerException();
        }
        else throw new AlreadyChosenStarter();

    }
}
