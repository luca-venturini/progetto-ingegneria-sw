package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Model.Worker;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalCardException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalPlaceWorkerException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.*;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private List<ClientController> clients = new ArrayList<>();
    private Match match;
    private CardSetupHandler cardSetupHandler;
    private PlaceWorkerSetupHandler placeWorkerSetupHandler;
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
            if(cardSetupHandler.getNextPlayer() != null)
                notifyAllClients(new SimplyNotification(player.getNickname()+" ha scelto la sua carta, tocca a "+cardSetupHandler.getNextPlayer()));
            else {
                notifyAllClients(new SimplyNotification("Inizio nuova fase, attendi..."));
                notifySingleClient(match.getChallenger(), new ChooseStartingPlayerNotification());
            }
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


    public void setStartingPlayer(String nickname, String startingPlayerNickname){
        if(nickname == match.getChallenger().getNickname()) {
            try {
                match.selectStarter(startingPlayerNickname);
                notifySingleClient(match.getChallenger(), new SetStartingPlayerResponse( true, "Giocatore iniziale settato"));
                notifyAllClients(new SimplyNotification("Ogni giocatore dovrà posizionare i propri workers"));
                placeWorkerSetupHandler = new PlaceWorkerSetupHandler(match.getPlayers(), match.getBoard());
                notifyAllClients(new PlaceWorkersNotification());
            } catch (AlreadyChosenStarterException e) {
                e.printStackTrace();
            } catch (InvalidNamePlayerException e) {
                notifySingleClient(match.getChallenger(), new SetStartingPlayerResponse( false, "Il nickname scelto non è disponibile"));
            } catch (NoPlayerException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("Error setStartingPlayer method in gameController");
        }
    }

    public void sendPossiblePlayers(ClientController clientController){
        List<String> playersNickname = new ArrayList<>();
        try {
            List<Player> players = match.getPlayers();
            for(Player p: players)
                playersNickname.add(p.getNickname());
        } catch (NoPlayerException e) {
            playersNickname = null;
            e.printStackTrace();
        }
        notifySingleClient(clientController, new AviablePlayersResponse(playersNickname));
    }


    public void placeWorkers(Player player, Point position){
        Point point = new Point(position.getX(), position.getY());
        try {
            placeWorkerSetupHandler.setWorkerPosition(player, position);
            if(placeWorkerSetupHandler.hasFinished())
                notifyAllClients(new SimplyNotification("Operazioni completate, fine fase di setUp"));
            else if(placeWorkerSetupHandler.getCurrentPlayer().equals(player))
                notifySingleClient(player, new PlaceWorkerResponse( true, false, "Primo worker posizionato, procedi col secondo", point ));
            else
                notifySingleClient(player, new PlaceWorkerResponse( true, true, "Secondo worker posizionato, attendi...", point ));
        } catch (IllegalPlaceWorkerException e) {
            notifySingleClient(player, new PlaceWorkerResponse( false, false, "Il worker non può essere posizionato in qualla casella",point ));
        }
    }

    public void sendPossibleWorkersSetupPoint(ClientController clientController){
        List<Point> points = placeWorkerSetupHandler.getPossiblePoint();
        notifySingleClient(clientController, new AviablePlaceWorkerPointResponse(points));
    }



    public int getNumOfPlayers(){
        return clients.size();
    }

}
