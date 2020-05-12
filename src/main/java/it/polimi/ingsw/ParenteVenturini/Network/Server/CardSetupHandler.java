package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalCardException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;

import java.util.List;

/**
 * class used to handle the phase in which challenger choose the possible cards and players can choose their card
 */
public class CardSetupHandler {

    /** list of players */
    private List<Player> players;
    /** the current player */
    private int currentPlayer;
    /** list of possible cards chosen by the challenger*/
    private List<Card> possibleCards;

    /**
     * init the class
     * @param possibleCards all the possible cards
     * @param players list of players
     * @param challenger the challenger
     */
    public CardSetupHandler(List<Card> possibleCards, List<Player> players, Player challenger) {
        this.possibleCards = possibleCards;
        this.players = players;
        this.players.remove(challenger);
        this.players.add(challenger);
        this.currentPlayer = 0;
    }

    /**
     * set the player's card
     * @param player the player
     * @param card the player's card
     * @throws NotYourTurnException thrown if the player is not doing the action during his turn
     * @throws IllegalCardException thrown if the card is incorrect
     */
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

    /**
     * get the next player that must choose his card
     * @return the name of the next player
     */
    public String getNextPlayer(){
        if(players.size() > currentPlayer)
            return players.get(currentPlayer).getNickname();
        else
            return null;
    }

    /**
     * get a list of all of the possible cards
     * @return listof cards
     */
    public List<Card> getPossibleCards(){
        return possibleCards;
    }
}
