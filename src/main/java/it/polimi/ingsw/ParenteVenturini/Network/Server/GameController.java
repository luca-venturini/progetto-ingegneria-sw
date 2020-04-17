package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.AlreadyPresentPlayerException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.InvalidTypeOfMatch;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoMorePlayersException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoPlayerException;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalCardException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.*;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private List<ClientController> clients = new ArrayList<>();
    private Match match;
    private CardSetupHandler cardSetupHandler;
    private Deck deck = new Deck();



    public GameController(int numOfPlayers){
        match = new Match();
        try {
            match.setTypeOfMatch(numOfPlayers);
        } catch (InvalidTypeOfMatch invalidTypeOfMatch) {
            invalidTypeOfMatch.printStackTrace();
        }
        System.out.println("Creata partita da "+numOfPlayers+" giocatori");
    }

    public Player addPlayer(ClientController client, String nickname){

        int i = 1;
        String originalNickname = nickname;
        while (!isValidNickname(nickname)){
            nickname = originalNickname+" ("+i+")";
            i++;
        }
        try {
            match.addPlayer(nickname);
            System.out.println("add player");
            for (Player p: match.getPlayers())
                System.out.println("---: "+p.getNickname());
        } catch (NoMorePlayersException | AlreadyPresentPlayerException | NoPlayerException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        clients.add(client);
        return match.selectPlayer(nickname);
    }

    public boolean isValidNickname(String nickname){
        return match.selectPlayer(nickname) == null;
    }

    public void startSetup(){
        if(match.getTypeOfMatch() == clients.size()) {
            notifyAllClients(new SimplyNotification( "E' iniziata la fase di setUp, tra poco tocca a te..."));
            try {
                match.setChallenger();
            } catch (NoPlayerException e) {
                e.printStackTrace();
            }
            Player challenger = match.getChallenger();
            notifySingleClient(challenger, new SelectCardNotification(deck.getCardNames(), match.getNumPlayers()));
        }
        else
            notifyAllClients(new SimplyNotification( "Attendi altri giocatori"));
    }

    public void notifyAllClients(MessageToClient msg){
        for (ClientController client: clients){
            client.sendMessage(msg);
        }
    }

    public void notifySingleClient(ClientController client, MessageToClient msg){
        client.sendMessage(msg);
    }

    public void notifySingleClient(Player player, MessageToClient msg){
        for (ClientController c: clients){
            if(c.getPlayer().getNickname().equals(player.getNickname())) {
                c.sendMessage(msg);
                break;
            }
        }
    }

    public void addCardsToMatch(String nickname, List<String> values) throws IllegalCardException {
        List<Card> chosenCards = new ArrayList<>();

        if(nickname.equals(match.getChallenger().getNickname())){
            for (String s: values){
                Card card = deck.selectByName(s);
                if(!chosenCards.contains(card))
                    chosenCards.add(card);
                else
                    throw new IllegalCardException();
            }

        }
        if(chosenCards.size() == match.getNumPlayers()){
            match.setChosenCards(chosenCards);
            try {
                cardSetupHandler = new CardSetupHandler(chosenCards, match.getPlayers(), match.getChallenger());
            } catch (NoPlayerException e) {
                e.printStackTrace();
            }
            notifyAllClients(new SimplyNotification( "A turno ogni giocatore sceglie una carta, inizia "+cardSetupHandler.getNextPlayer()));
            notifyAllClients(new ChooseCardNotification());
        }
        else{
            throw new IllegalCardException();
        }
    }



    public void setPlayerCard(Player player, String card){
        try {
            cardSetupHandler.setCard(player, deck.selectByName(card));
            notifySingleClient(player, new SetPlayerCardResponse( true, "Carta aggiunta"));
            notifyAllClients(new SimplyNotification(player.getNickname()+" ha scelto la sua carta, tocca a "+cardSetupHandler.getNextPlayer()));
        } catch (NotYourTurnException e) {
            notifySingleClient(player, new SetPlayerCardResponse( false, "Non è il tuo turno"));
        } catch (IllegalCardException e) {
            notifySingleClient(player, new SetPlayerCardResponse( false, "La carta scelta non è disponibile"));
        }
    }

    public void sendPossibleCards(ClientController clientController){
        List<String> cardsName = new ArrayList<>();
        for(Card c: cardSetupHandler.getPossibleCards()){
            cardsName.add(c.getName());
        }
        notifySingleClient(clientController, new AviableCardResponse(cardsName));
    }

    public int getNumOfPlayers(){
        return clients.size();
    }

}
