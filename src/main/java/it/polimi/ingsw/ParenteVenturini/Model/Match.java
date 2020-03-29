package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;

import java.util.List;

public class Match {
    private List<Player> players;
    private List<Card> cards;
    private Player challenger;
    private Board board;
    private Deck deck;


    public void initBoard(){
        Board.createBoard();
    }

    public Board getBoard() {
        return this.board;
    }

    public void initDeck() {
        Deck.createDeck();
    }

}
