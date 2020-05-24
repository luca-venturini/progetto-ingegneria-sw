package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * the Match class
 */
public class Match {
    /** list of players */
    private List<Player> players;
    /** list of chosen cards */
    private List<Card> chosenCards;
    /** the board */
    private Board board;
    /** the deck */
    private Deck deck;
    /** starting player */
    private Player starter;
    /** the type of match */
    private int typeOfMatch;
    /** the container that contains the active opponent effects */
    private OpponentEffectContainer opponentEffectContainer;
    /** reference to the turn */
    private Turn turn;
    /** the chosen challenger */
    private Player challenger;

    /**
     * init the match class
     */
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

    /**
     * set turn attribute to the match
     */
    public void setTurn() {
        this.turn = new Turn(players,opponentEffectContainer);
    }

    /**
     * add a new player to the match
     * @param nickname the player nickname
     * @throws NoMorePlayersException thrown if the match is full
     * @throws AlreadyPresentPlayerException thrown if the nickname is already taken in this match
     */
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

    /**
     * check the gameOver due to impossible walk or building
     * @return true if the player has lost
     */

    public boolean directGameOver(){
        Move move = turn.getCurrentPlayer().getMove();
        Worker currentWorker = turn.getCurrentWorker();
        if(move == null || currentWorker == null) return false;
        if(move.forcedMovement()) {
            System.out.println("game over movement");
            return gameOverMovement(move, board, currentWorker);
        }
        if(move.forcedBuilding()) {
            System.out.println("game over building");
            return gameOverBuilding(move, board, currentWorker);
        }
        if(!move.forcedMovement() && !move.forcedBuilding() && !move.getHasWalkedandBuilt()) {
            System.out.println("game over not forced");
            return gameOverMovement(move, board, currentWorker) && gameOverBuilding(move, board, currentWorker);
        }
        return false;
    }

    /**
     * check the player's game over
     * @return true if the player has lost
     */

    public boolean gameOver()  {
        Move move = this.getTurn().getCurrentPlayer().getMove();
        if(move == null) return false;
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

    /**
     * check if the worker can't walk
     * @param move the player's walk
     * @param board the current board
     * @param currentWorker the current worker
     * @return true if the player can't walk
     */
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

    /**
     *
     * check if the worker can't build
     * @param move the player's build
     * @param board the current board
     * @param currentWorker the current worker
     * @return true if the player can't build
     */
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

    /**
     * get the game board
     * @return the used board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * get the challenger
     * @return the selected challenger
     */
    public Player getChallenger() {
        return challenger;
    }

    /**
     *
     * @return list of players in game
     * @throws NoPlayerException thrown if the list is empty
     */
    public List<Player> getPlayers() throws NoPlayerException {
        if( !players.isEmpty() )
            return players;
        else throw new NoPlayerException();
    }

    /**
     * get the container which contains the opponent effects activated
     * @return the opponentEffectContainer reference
     */
    public OpponentEffectContainer getOpponentEffectContainer() {
        return opponentEffectContainer;
    }

    /**
     * get the number of players
     * @return the number of the players in game
     */
    public int getNumPlayers() {
        if( !players.isEmpty() )
            return players.size();
        else return 0;
    }

    /**
     * get the type of match (2 or 3 players)
     * @return the type of match, 2 or 3 players
     */
    public int getTypeOfMatch() {
        return typeOfMatch;
    }

    /**
     * get the starting player
     * @return the starting player
     */
    public Player getStarter() {
        return starter;
    }

    /**
     * set if the match is a 3 or 2 players match
     * @param typeOfMatch the type of match (2 or 3)
     * @throws InvalidTypeOfMatch thrown if the value is incorrect
     */
    public void setTypeOfMatch(int typeOfMatch) throws InvalidTypeOfMatch {
        if(typeOfMatch==3 || typeOfMatch==2)
            this.typeOfMatch = typeOfMatch;
        else throw new InvalidTypeOfMatch();
    }

    /**
     * select a random challenger
     * @throws NoPlayerException thrown if there are no players
     */
    public void setChallenger() throws NoPlayerException {
        if( !players.isEmpty() ) {
            Random rand = new Random();
            this.challenger = players.get(rand.nextInt(players.size()));
        }
        else throw new NoPlayerException();
    }

    /**
     * select a player by name
     * @param name the String name
     * @return reference to the selected player, null if the player doesn't exists
     */
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

    /**
     * pick a card from the deck
     * @param name the name of the card
     * @throws InvalidCardException thrown if the name given doesn't match with any card
     * @throws NoMoreCardsException thrown if you can't pick others card
     */
    public void selectCardFromDeck(String name) throws InvalidCardException, NoMoreCardsException {
        Card c = this.deck.selectByName(name);
        if (chosenCards.size() < getTypeOfMatch()){
            if (c != null && !chosenCards.contains(c)) {
                chosenCards.add(c);
            } else throw new InvalidCardException();
        }else throw  new NoMoreCardsException();
    }

    /**
     * select the starting player by his nickname
     * @param nickname the starting player nickname
     * @throws AlreadyChosenStarterException thrown if the starting player has already been chosen
     * @throws InvalidNamePlayerException thrown if the given name doesn't exists in the match
     * @throws NoPlayerException thrown if there are no players
     */
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

    /**
     * get the list of chosen cards
     * @return list of chosen cards
     */

    public List<Card> getChosenCards(){
        return chosenCards;
    }

    /**
     * order the players to start playing
     * @throws NoStarterException thrown if the starter hasn't been selected
     * @throws NoPlayerException thrown if there are no players
     */

    /*
    public void orderPlayers2() throws NoStarterException, NoPlayerException {
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

     */

    public void orderPlayers() throws NoStarterException, NoPlayerException {
        if( !players.isEmpty() ) {
            if (this.starter != null) {
                List<Player> p = new ArrayList<>();
                int index = players.indexOf(starter);
                p.add(this.starter);
                for(int i = index+1; i<players.size(); i++){
                    p.add(players.get(i));
                }
                for(int j = 0; j<index; j++){
                    p.add(players.get(j));
                }
                this.players = p;
            } else throw new NoStarterException();
        }
        else throw new NoPlayerException();
        System.out.println("Length: "+players.size());
        System.out.println("Order: ");
        for(Player p: players){
            System.out.println("Player: "+p.getNickname());
        }
    }

    /**
     * set the list of cards chosen by the challenger
     * @param cards the list of chosen cards
     */
    public void setChosenCards(List<Card> cards){
        chosenCards = cards;
    }

    /**
     * return a reference to turn class
     * @return reference to turn class
     */
    public Turn getTurn() {
        return turn;
    }

    /**
     * check if a player has won during another player turn, thanks to special effects
     * @return the winner
     */
    public Player outOfTurnWin(){
        for (Player p: players){
            if(p.getCard().getWinCheck() != null && p.getCard().getWinCheck().outOfTurnWon(board))
                return p;
        }
        return null;
    }

    /**
     * delete a player from the match
     * @param player the player you want to delete
     */
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
