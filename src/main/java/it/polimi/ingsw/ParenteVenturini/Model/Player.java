package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.BasicWinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Checks.WinCheck;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.OpponentEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Moves.Move;

import java.util.ArrayList;
import java.util.List;

/**
 * this class represent a single player
 */
public class Player{

    /** the player's card */
    private Card card;
    /** the player's workers*/
    private List<Worker> workers;
    /** the player nickname */
    private String nickname;
    /** the player win condition */
    private WinCheck winCondition;
    /** the match that the player is playing */
    private Match match;
    /** the player's move*/
    private Move move;


    /**
     * set new player
     * @param nickname player's nickanme
     * @param match the match of the player
     */
    public Player(String nickname, Match match) {
        this.nickname = nickname;
        workers = new ArrayList<>();
        this.match= match;
        this.move= null;
    }

    /**
     * set the player's card
     * @param card the card that has to be set
     */
    public void setCard(Card card){
        this.card = card;
        setWinCondition();
    }

    /**
     * set the winCondition after the card has been set
     */
    private void setWinCondition(){
        if(card == null || card.getWinCheck() == null)
            this.winCondition = new BasicWinCheck();
        else
            this.winCondition = card.getWinCheck();
    }

    /**
     * add the worker to the player and place it on the board
     * @param colour the worker color
     * @param point the point where the worker is set
     * @param board the used board
     */
    public void placeWorker(int colour , Point point, Board board){
        Worker worker= new Worker(point, this, colour);
        addWorker(worker);
        board.setWorker(worker);
    }

    /**
     * add a new worker to the player
     * @param worker the new worker
     */
    private void addWorker(Worker worker){
        workers.add(worker);
    }

    /**
     * get a worker by his number
     * @param num the number of the worker
     * @return the selected worker
     */
    public Worker selectWorker(int num){
        return workers.get(num);
    }

    /**
     * get a new Move object
     * @return a new Move object
     */
    public Move callMove(){
        return card.getMove();
    }

    /**
     * get the actual player's move
     * @return the actual player's move
     */
    public Move getMove() {
        return move;
    }

    /**
     * check if the player ha won
     * @param board the used board
     * @param worker specific worker
     * @param players list of all of the players, to check OpponentEffect on the victory
     * @return true if the player has won
     */
    public boolean hasWon(Board board, Worker worker, List<Player> players){
        return winCondition.hasWon(board, worker, players);
    }

    /**
     * set a new move
     * @param move the move
     */
    public void setMove(Move move) {
        this.move = move;
    }

    /**
     * return the player's card
     * @return the player's card
     */
    public Card getCard() {
        return card;
    }

    /**
     * set the player's nickname
     * @param nickname the player's nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * set a new card to the player
     * @param nameCard the name of the card
     * @throws InvalidCardException thrown if the card doesn't exist
     */
    public void chooseCard(String nameCard) throws InvalidCardException {
        List<Card> cards=match.getChosenCards();
        for(Card c:cards) {
            if ( nameCard.equals(c.getName()) ) {
                setCard(c);
            }
            else throw new InvalidCardException();
        }
    }

    /**
     * get the player's nickname
     * @return the player's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * get the OpponentEffect of the card, if there is one
     * @return the opponent effect of the player's card
     */
    public OpponentEffect getOpponentEffectPlayer(){
        return card.getOpponentEffect();
    }

    /**
     * the current worker set in match walk to a specified point
     * @param p the specified point
     * @throws OpponentEffectException thrown if the action is not valid due to an OpponentEffect
     * @throws NotPossibleEndMoveException thrown if the action is not valid
     * @throws AlreadyWalkedException thrown if the action is not valid because you have already walked
     * @throws AlreadyBuiltException thrown if the action is not valid because you have already built
     * @throws IllegalBuildingException thrown if you can not build
     * @throws endedMoveException thrown if you can't do anything else
     * @throws IllegalMovementException thrown if the action is not valid
     */
    public void walk(Point p) throws OpponentEffectException, NotPossibleEndMoveException, AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = match.getTurn().getCurrentWorker();
        if(myWorker == null) throw new IllegalMovementException();
        if(match.getOpponentEffectContainer().checkMovementPoint(p, myWorker,match.getBoard())){
                move.walk(p, match.getBoard(), myWorker);
                OpponentEffect temp = card.getOpponentEffect();
                if(temp!= null && temp.isMovementEffectEnabled(p, myWorker.getLastPosition(), match.getBoard())){
                    match.getOpponentEffectContainer().addEffect(card.getOpponentEffect());
                }
        }
        else
            throw new OpponentEffectException();
    }

    /**
     * the current worker set in match build in a specified point
     * @param p the specified point
     * @throws OpponentEffectException thrown if the action is not valid due to an OpponentEffect
     * @throws NotPossibleEndMoveException thrown if the action is not valid
     * @throws AlreadyWalkedException thrown if the action is not valid because you have already walked
     * @throws AlreadyBuiltException thrown if the action is not valid because you have already built
     * @throws IllegalBuildingException thrown if you can not build
     * @throws endedMoveException thrown if you can't do anything else
     * @throws IllegalMovementException thrown if the action is not valid
     * @throws OutOfOrderMoveException thrown if the movement isn't done in the right order
     */
    public void build(Point p) throws OpponentEffectException, AlreadyBuiltException, IllegalBuildingException, OutOfOrderMoveException, endedMoveException, IllegalMovementException, NotPossibleEndMoveException, AlreadyWalkedException {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = match.getTurn().getCurrentWorker();
        if(myWorker == null) throw new IllegalBuildingException();
        if(match.getOpponentEffectContainer().checkConstructionPoint(p, myWorker, match.getBoard()) ){
                move.build(p, match.getBoard(), myWorker);
                OpponentEffect temp = card.getOpponentEffect();
                if(temp!= null && temp.isConstructionEffectEnabled(p, myWorker.getLastPosition(), match.getBoard())){
                    match.getOpponentEffectContainer().addEffect(card.getOpponentEffect());
                }
        }
        else
            throw new OpponentEffectException();
    }

    /**
     * the current worker will do the special build
     * @param p the specified point
     * @throws OpponentEffectException thrown if the action is not valid due to an OpponentEffect
     * @throws AlreadyWalkedException thrown if the action is not valid because you have already walked
     * @throws AlreadyBuiltException thrown if the action is not valid because you have already built
     * @throws IllegalBuildingException thrown if you can not build
     * @throws endedMoveException thrown if you can't do anything else
     * @throws IllegalMovementException thrown if the action is not valid
     * @throws OutOfOrderMoveException thrown if the movement isn't done in the right order
     */
    public void specialBuild(Point p) throws OpponentEffectException, OutOfOrderMoveException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, AlreadyWalkedException {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = match.getTurn().getCurrentWorker();
        if(myWorker == null) throw new IllegalBuildingException();
        if(match.getOpponentEffectContainer().checkConstructionPoint(p, myWorker, match.getBoard())){
                move.specialBuild(p, match.getBoard(), myWorker);
                OpponentEffect temp= card.getOpponentEffect();
                if(temp!= null && temp.isConstructionEffectEnabled(p, myWorker.getLastPosition(), match.getBoard())){
                    match.getOpponentEffectContainer().addEffect(card.getOpponentEffect());
                }
        }
        else
            throw new OpponentEffectException();
    }

    /**
     * return a list of possible points where the current worker can walk
     * @return list of possible points
     * @throws AlreadyWalkedException thrown if the player can't walk anymore
     */
    public List<Point> getPossibleMovements() throws AlreadyWalkedException {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = match.getTurn().getCurrentWorker();
        if(myWorker == null) return new ArrayList<Point>();
        List<Point> temp = move.possibleMovements(match.getBoard(), myWorker);
        temp = match.getOpponentEffectContainer().removeMovementPoint(temp, myWorker.getPosition(), myWorker.getEffect(), match.getBoard());
        return temp;
    }

    /**
     * return a list of possible points where the current worker can build
     * @return list of possible points
     * @throws OutOfOrderMoveException thrown if the action is not possible in that moment
     * @throws AlreadyBuiltException thrown if the player can't build anymore
     * @throws AlreadyWalkedException thrown if the player can't build because he has already walked
     */
    public List<Point> getPossibleBuildings() throws OutOfOrderMoveException, AlreadyBuiltException, AlreadyWalkedException {
        if( this.move== null )
            this.move = callMove();
        Worker myWorker = match.getTurn().getCurrentWorker();
        if(myWorker == null) return new ArrayList<Point>();
        List<Point> temp = move.possibleBuildings(match.getBoard(), myWorker);
        temp = match.getOpponentEffectContainer().removeConstructionPoint(temp, myWorker.getPosition(), myWorker.getEffect(), match.getBoard());
        return temp;
    }

    /**
     * the player end his turn
     * @throws NotPossibleEndMoveException thrown if the player can't end his turn
     */
    public void endMove() throws NotPossibleEndMoveException {
        if(this.move != null) {
            if (this.move.getHasEnded() || this.move.getHasWalkedandBuilt()) {
                move = null;
                match.getTurn().setNextPlayer();
            } else throw new NotPossibleEndMoveException();
        }else throw new NotPossibleEndMoveException();
    }

    /**
     * get a list of player's workers
     * @return the list of player's workers
     */
    public List<Worker> getWorkers(){
        return workers;
    }

    /**
     * check if two players are equal
     * @param other the other player
     * @return true if the two players are equal
     */
    public boolean equals(Player other){
        return this.nickname.equals(other.nickname);
    }

}
