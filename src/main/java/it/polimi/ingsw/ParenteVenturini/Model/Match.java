package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Player.Player;

import java.util.ArrayList;
import java.util.List;

public class Match {
    private List<Player> players;
    private List<Card> cards;
    private Player challenger;
    private Board board;
    private Deck deck;

    public Match(){
        this.board= new Board();
        this.deck= new Deck();
        this.players= new ArrayList<Player>();
        this.challenger=null;
    }

    public void addPlayer(String nickname){
        Player p= new Player(nickname);
        players.add(p);
    }

}
