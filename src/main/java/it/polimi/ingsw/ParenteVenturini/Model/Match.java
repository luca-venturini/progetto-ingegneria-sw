package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.AlreadyChosenStarter;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidCardException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidNamePlayerException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoMorePlayersException;

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

    public Match(){
        this.board= new Board();
        this.deck= new Deck();
        this.players= new ArrayList<Player>();
        this.chosenCards= new ArrayList<Card>();
        this.opponentEffectContainer = new OpponentEffectContainer();
        this.typeOfMatch = 2;
    }

    public void addPlayer(String nickname) throws NoMorePlayersException {
        if(getnumOfPlayers() < getTypeOfMatch()) {
            Player p = new Player(nickname,this);
            players.add(p);
        }
        else throw new NoMorePlayersException();
    }

    public int getnumOfPlayers(){
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
