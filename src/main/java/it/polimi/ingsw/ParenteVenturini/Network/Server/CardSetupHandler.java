package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalCardException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;

import java.util.List;

public class CardSetupHandler {

    private List<Player> players;
    private int currentPlayer;
    private List<Card> possibleCards;

    public CardSetupHandler(List<Card> possibleCards, List<Player> players, Player challenger) {
        this.possibleCards = possibleCards;
        this.players = players;
        this.players.remove(challenger);
        this.players.add(challenger);
        this.currentPlayer = 0;
    }

    public void setCard(Player player, Card card) throws NotYourTurnException, IllegalCardException {
        if(currentPlayer < players.size() && players.get(currentPlayer).equals(player) && player.getCard() == null) {
            if(possibleCards.contains(card)) {
                player.setCard(card);
                currentPlayer++;
                possibleCards.remove(card);
            }
            else throw new IllegalCardException();
        }
        else{
            throw new NotYourTurnException();
        }
    }

    public String getNextPlayer(){
        if(players.size() > currentPlayer)
            return players.get(currentPlayer).getNickname();
        else
            return null;
    }

    public List<Card> getPossibleCards(){
        return possibleCards;
    }
}
