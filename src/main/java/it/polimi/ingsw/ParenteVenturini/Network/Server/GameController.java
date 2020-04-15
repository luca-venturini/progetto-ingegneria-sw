package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoMorePlayersException;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalCardException;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.*;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private List<ClientController> clients = new ArrayList<>();
    private Match match;
    private boolean choosingCardPhase;

    public GameController(int numOfPlayers){
        match = new Match();
        match.setTypeOfMatch(numOfPlayers);
        System.out.println("Creata partita da "+numOfPlayers+" giocatori");
    }

    public Player addPlayer(ClientController client, String nickname){

        int i = 1;
        String originalNickname = nickname;
        while (match.selectPlayer(nickname) != null){
            nickname = originalNickname+" ("+i+")";
            i++;
        }
        try {
            match.addPlayer(nickname);
            System.out.println("add player");
            for (Player p: match.getPlayers())
                System.out.println("---: "+p.getNickname());
        } catch (NoMorePlayersException e) {
            System.out.println("erroreeee");
            e.printStackTrace();
        }
        clients.add(client);
        return match.selectPlayer(nickname);
    }

    public void startSetup(){
        if(match.getTypeOfMatch() == clients.size()) {
            notifyAllClients(new SetUpNotification());
            match.setChallenger();
            Player challenger = match.getChallenger();
            Deck deck = new Deck();
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
        Deck deck = new Deck();
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
            notifyAllClients(new SimplyNotification( "A turno ogni giocatore sceglie una carta, tra poco tocca a te..."));

        }
        else{
            throw new IllegalCardException();
        }
    }

    public int getNumOfPlayers(){
        return clients.size();
    }

}
