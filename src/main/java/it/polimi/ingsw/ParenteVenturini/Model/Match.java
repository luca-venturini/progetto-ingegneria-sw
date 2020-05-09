package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
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
    private Turn turn;
    private Player challenger;

    public Match(){
        this.board= new Board();
        this.deck= new Deck();
        this.players= new ArrayList<>();
        this.chosenCards= new ArrayList<>();
        this.opponentEffectContainer = new OpponentEffectContainer();
        this.typeOfMatch = 2;
        this.challenger=null;
        this.turn = null;
    }

    public void setTurn() {
        this.turn = new Turn(players,opponentEffectContainer);
    }

    public void addPlayer(String nickname) throws NoMorePlayersException, AlreadyPresentPlayerException {
        if(getNumPlayers() < getTypeOfMatch()) {
            if(selectPlayer(nickname)==null) {
                Player p = new Player(nickname, this);
                players.add(p);
            }
            else throw new AlreadyPresentPlayerException();
        }
        else throw new NoMorePlayersException();
    }



    public boolean directGameOver(){
        Move move = this.getTurn().getCurrentPlayer().getMove();
        Worker currentWorker = turn.getCurrentWorker();
        if(move.forcedBuilding()) {
            return gameOverBuilding(move, board, currentWorker);
        }
        return false;
    }

    public boolean gameOver()  {
        Move move = this.getTurn().getCurrentPlayer().getMove();

        List<Worker> workers = this.getTurn().getCurrentPlayer().getWorkers();
        boolean result = false;
        if(move.forcedMovement()){
            result = gameOverMovement(move, board, workers.get(0)) && gameOverMovement(move, board, workers.get(1));
        }
        else if(move.forcedBuilding()){
            result = gameOverBuilding(move, board, workers.get(0)) && gameOverBuilding(move, board, workers.get(1));
        }
        else if( !move.forcedMovement() && !move.forcedBuilding())
            return  (gameOverMovement(move, board, workers.get(0)) && gameOverMovement(move, board, workers.get(0)) ) && (gameOverBuilding(move, board, workers.get(0)) && gameOverBuilding(move, board, workers.get(1)));
        return result;
    }

    private boolean gameOverMovement(Move move, Board board, Worker currentWorker)  {
        List<Point> points = null;
        try {
            points = move.possibleMovements(board, currentWorker);
        } catch (AlreadyWalkedException e) {
            return true;
        }
        points = opponentEffectContainer.removeMovementPoint(points, currentWorker.getPosition(), currentWorker.getEffect(), board);
        return points.isEmpty();
    }

    private boolean gameOverBuilding(Move move, Board board, Worker currentWorker)  {
        List<Point> points = null;
        try {
            points = move.possibleBuildings(board, currentWorker);
        } catch (OutOfOrderMoveException | AlreadyBuiltException | AlreadyWalkedException e) {
            return true;
        }
        points = opponentEffectContainer.removeConstructionPoint(points, currentWorker.getPosition(), currentWorker.getEffect(), board);
        return points.isEmpty();
    }

    public Board getBoard() {
        return board;
    }

    public Player getChallenger() {
        return challenger;
    }

    public List<Player> getPlayers() throws NoPlayerException {
        if( !players.isEmpty() )
            return players;
        else throw new NoPlayerException();
    }

    public OpponentEffectContainer getOpponentEffectContainer() {
        return opponentEffectContainer;
    }

    public int getNumPlayers() {
        if( !players.isEmpty() )
            return players.size();
        else return 0;
    }

    public int getTypeOfMatch() {
        return typeOfMatch;
    }

    public Player getStarter() {
        return starter;
    }

    public void setTypeOfMatch(int typeOfMatch) throws InvalidTypeOfMatch {
        if(typeOfMatch==3 || typeOfMatch==2)
            this.typeOfMatch = typeOfMatch;
        else throw new InvalidTypeOfMatch();
    }

    public void setChallenger() throws NoPlayerException {
        if( !players.isEmpty() ) {
            Random rand = new Random();
            this.challenger = players.get(rand.nextInt(players.size()));
        }
        else throw new NoPlayerException();
    }

    public Player selectPlayer(String name){
        if( !players.isEmpty() ) {
            for (Player p : players) {
                if (name.equals(p.getNickname())) {
                    return p;
                }
            }
            return null;
        }
        else return null;
    }

    public void selectCardFromDeck(String name) throws InvalidCardException, NoMoreCardsException {
        Card c = this.deck.selectByName(name);
        if (chosenCards.size() < getTypeOfMatch()){
            if (c != null && !chosenCards.contains(c)) {
                chosenCards.add(c);
            } else throw new InvalidCardException();
        }else throw  new NoMoreCardsException();
    }

    public void selectStarter(String nickname) throws AlreadyChosenStarterException, InvalidNamePlayerException, NoPlayerException {
        if( !players.isEmpty() ) {
            if (starter == null) {
                Player p = selectPlayer(nickname);
                if (p != null) {
                    this.starter = p;
                    try {
                        orderPlayers();
                    } catch (NoStarterException | NoPlayerException e) {
                        e.printStackTrace();
                    }
                } else throw new InvalidNamePlayerException();
            } else throw new AlreadyChosenStarterException();
        }
        else throw new NoPlayerException();
    }

    public List<Card> getChosenCards(){
        return chosenCards;
    }

    public void orderPlayers() throws NoStarterException, NoPlayerException {
        if( !players.isEmpty() ) {
            if (this.starter != null) {
                List<Player> p = new ArrayList<>();
                p.add(this.starter);
                for (Player i : players) {
                    if (!i.equals(starter)) {
                        p.add(i);
                    }
                }
                this.players = p;
            } else throw new NoStarterException();
        }
        else throw new NoPlayerException();
    }

    public void setChosenCards(List<Card> cards){
        chosenCards = cards;
    }

    public Turn getTurn() {
        return turn;
    }

    public Player outOfTurnWin(){
        for (Player p: players){
            if(p.getCard().getWinCheck() != null && p.getCard().getWinCheck().outOfTurnWon(board))
                return p;
        }
        return null;
    }

    public void deletePlayer(Player player){
        try {
            this.chosenCards.remove(player.getCard());
            List<Worker> workers = getBoard().getWorkers();
            List<Worker> deletedworkers = new ArrayList<>();
            for(Worker w: workers){
                if(w.getPlayer().equals(player)){
                    deletedworkers.add(w);
                }
            }
            for(Worker dw: deletedworkers){
                getBoard().getWorkers().remove(dw);
            }
            OpponentEffect effect=player.getOpponentEffectPlayer();
            if(effect != null){
                opponentEffectContainer.removeEffect(effect);
            }
            this.getPlayers().remove(player);
        } catch (NoPlayerException e) {
            e.printStackTrace();
        }
    }
}
