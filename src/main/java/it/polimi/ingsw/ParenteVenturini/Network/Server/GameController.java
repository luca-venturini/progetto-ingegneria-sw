package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.*;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Card;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.Deck;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalCardException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalPlaceWorkerException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NoPossibleActionException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.NotYourTurnException;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameController {
    private List<ClientController> clients = new ArrayList<>();
    private Match match;
    private CardSetupHandler cardSetupHandler;
    private PlaceWorkerSetupHandler placeWorkerSetupHandler;
    private MoveHandler moveHandler;
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
            notifyAllClients(new ChooseCardNotification());
            notifyAllClients(new SimplyNotification( "A turno ogni giocatore sceglie una carta, inizia "+cardSetupHandler.getNextPlayer()));
        }
        else{
            throw new IllegalCardException();
        }
    }

    public void setPlayerCard(Player player, String card){
        if (card == null){
            notifySingleClient(player, new SetPlayerCardResponse( false, "Scegli una carta"));
        }
        else {
            try {
                cardSetupHandler.setCard(player, deck.selectByName(card));

                if (cardSetupHandler.getNextPlayer() != null) {
                    notifySingleClient(player, new SetPlayerCardResponse(true, "Carta aggiunta"));
                    notifyAllClients(new SimplyNotification(player.getNickname() + " ha scelto la sua carta, tocca a " + cardSetupHandler.getNextPlayer()));
                }
                else {
                    //notifyAllClients(new SimplyNotification("Inizio nuova fase, attendi..."));
                    notifyAllClients(new WaitNotification());
                    notifySingleClient(match.getChallenger(), new ChooseStartingPlayerNotification());
                }
            } catch (NotYourTurnException e) {
                notifySingleClient(player, new SetPlayerCardResponse(false, "Non è il tuo turno"));
            } catch (IllegalCardException e) {
                notifySingleClient(player, new SetPlayerCardResponse(false, "La carta scelta non è disponibile"));
            }
        }
    }

    public void sendPossibleCards(ClientController clientController){
        List<String> cardsName = new ArrayList<>();
        for(Card c: cardSetupHandler.getPossibleCards()){
            cardsName.add(c.getName());
        }
        notifySingleClient(clientController, new AvailableCardResponse(cardsName));
    }


    public void setStartingPlayer(String nickname, String startingPlayerNickname){
        if(nickname.equals(match.getChallenger().getNickname())) {
            try {
                match.selectStarter(startingPlayerNickname);
                notifySingleClient(match.getChallenger(), new SetStartingPlayerResponse( true, "Giocatore iniziale settato"));
                notifyAllClients(new SimplyNotification("Ogni giocatore dovrà posizionare i propri workers"));
                System.out.println("Giocatore scelto come iniziale: "+match.getStarter().getNickname());
                placeWorkerSetupHandler = new PlaceWorkerSetupHandler(match.getPlayers(), match.getBoard());
                notifyAllClients(new PlaceWorkersNotification());
            } catch (AlreadyChosenStarterException | NoPlayerException e) {
                e.printStackTrace();
            } catch (InvalidNamePlayerException e) {
                notifySingleClient(match.getChallenger(), new SetStartingPlayerResponse( false, "Il nickname scelto non è disponibile"));
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
        notifySingleClient(clientController, new AvailablePlayersResponse(playersNickname));
    }


    public void placeWorkers(Player player, Point position){
        Point point = new Point(position.getX(), position.getY());
        try {
            placeWorkerSetupHandler.setWorkerPosition(player, position);
            if(placeWorkerSetupHandler.hasFinished()){
                notifyAllClients(new SimplyNotification("Operazioni completate, fine fase di setUp"));
                notifyAllClients(new SimplyNotification("Inizio della fase di gioco"));
                sendBoard();
                match.setTurn();
                moveHandler= new MoveHandler(this.match);
                notifyYourTurn();
            }
            else if(placeWorkerSetupHandler.getCurrentPlayer().equals(player))
                notifySingleClient(player, new PlaceWorkerResponse( true, false, "Primo worker posizionato, procedi col secondo", position ));
            else
                notifySingleClient(player, new PlaceWorkerResponse( true, true, "Secondo worker posizionato, attendi...", position ));
        } catch (IllegalPlaceWorkerException e) {
            notifySingleClient(player, new PlaceWorkerResponse( false, false, "Il worker non può essere posizionato in qualla casella",position ));
        }
    }

    public void sendPossibleWorkersSetupPoint(ClientController clientController){
        List<Point> points = placeWorkerSetupHandler.getPossiblePoint();
        notifySingleClient(clientController, new AvailablePlaceWorkerPointResponse(points));
    }

    public void sendBoard() {
        Block[][] blocks= new Block[5][5];
        List<Point> positionworker = new ArrayList<>();
        List<String> colours= new ArrayList<>();
        List<String> index= new ArrayList<>();
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                blocks[i][j]=match.getBoard().getBlock(i,j);
            }
        }
        List<Worker> workers=match.getBoard().getWorkers();
        for(Worker w: workers) {
            positionworker.add(w.getPosition());
            colours.add(String.valueOf(w.getColour()));
            if(w.getPosition().equals(w.getPlayer().selectWorker(0).getPosition())){
                index.add("1");
            }
            else {
                index.add("2");
            }
        }
        notifyAllClients(new BoardUpdateNotification(blocks,positionworker,colours,index) );
    }

    public void notifyYourTurn(){
        if(match.gameOver()) {
            notifySingleClient(match.getTurn().getCurrentPlayer(), new GameOverNotification());
            manageGameOver();
        }
        else {
            notifyAllClients(new SimplyNotification("E' il turno di " + match.getTurn().getCurrentPlayer().getNickname()));
            notifySingleClient(match.getTurn().getCurrentPlayer(), new YourTurnNotification());
            System.out.println("Turno: " + match.getTurn().getNumTurn() + " Giocatore: " + match.getTurn().getCurrentPlayer().getNickname());
        }
    }

    public void manageGameOver(){
        if (match.getTypeOfMatch() == 2) {
            match.getTurn().setNextPlayer();
            notifyAllClients(new WinNotification(match.getTurn().getCurrentPlayer().getNickname()));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            disconnectAllPlayers();
        } else {
            notifyAllClients(new SimplyNotification((match.getTurn().getCurrentPlayer().getNickname()+" ha perso")));
            Player delplayer=match.getTurn().getCurrentPlayer();
            match.getTurn().setNextPlayer();
            match.deletePlayer(delplayer);
            notifyYourTurn();
        }
    }

    public void selectWorker(ClientController clientController, String nickname, int index){
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)){
            match.getTurn().setActualWorker( match.selectPlayer(nickname).selectWorker(index-1) );
            notifySingleClient(clientController,new SelectWorkerResponse("Worker selezionato",true));
        }
        else notifySingleClient(clientController,new SelectWorkerResponse("Non è il tuo turno",false));
    }

    public void doMove(ClientController clientController, String typeOfMove,String nickname){
        moveHandler.init();
        List<Point> points;
        if(match.directGameOver()){
            notifySingleClient(match.getTurn().getCurrentPlayer(), new GameOverNotification());
            manageGameOver();
        }
        else {
            switch (typeOfMove) {
                case "Movement":
                    try {
                        points = moveHandler.getMovementsActions(nickname);
                        notifySingleClient(clientController, new ActionResponse(points));
                    } catch (NotYourTurnException e) {
                        notifySingleClient(clientController, new ActionNotification("Non è il tuo turno"));
                    } catch (NoPossibleActionException e) {
                        notifySingleClient(clientController, new ActionNotification("Nessuna azione possibile. Seleziona un altro worker"));
                    } catch (AlreadyWalkedException e) {
                        notifySingleClient(clientController, new ActionNotification("Hai già mosso"));
                    }
                    break;

                case "Construction":
                    try {
                        points = moveHandler.getConstructionActions(nickname);
                        notifySingleClient(clientController, new ActionResponse(points));
                    } catch (NotYourTurnException e) {
                        notifySingleClient(clientController, new ActionNotification("Non è il tuo turno"));
                    } catch (NoPossibleActionException e) {
                        notifySingleClient(clientController, new ActionNotification("Nessuna azione possibile"));
                    } catch (OutOfOrderMoveException e) {
                        notifySingleClient(clientController, new ActionNotification("Devi prima muovere"));
                    } catch (AlreadyBuiltException e) {
                        notifySingleClient(clientController, new ActionNotification("Hai già costruito"));
                    } catch (AlreadyWalkedException e) {
                        e.printStackTrace();
                    }
                    break;

                case "SpecialConstruction":
                    try {
                        points = moveHandler.getSpecialConstructionActions(nickname);
                        notifySingleClient(clientController, new ActionResponse(points));
                    } catch (NotYourTurnException e) {
                        notifySingleClient(clientController, new ActionNotification("Non è il tuo turno"));
                    } catch (NoPossibleActionException e) {
                        notifySingleClient(clientController, new ActionNotification("Nessuna azione possibile"));
                    } catch (OutOfOrderMoveException e) {
                        notifySingleClient(clientController, new ActionNotification("Devi prima muovere"));
                    } catch (AlreadyBuiltException e) {
                        notifySingleClient(clientController, new ActionNotification("Hai già costruito"));
                    } catch (AlreadyWalkedException e) {
                        e.printStackTrace();
                    }
                    break;

                case "EndMove":
                    try {
                        moveHandler.doEndMove(nickname);
                        notifySingleClient(clientController, new EndMoveResponse("Turno terminato", true));
                        notifyYourTurn();
                    } catch (NotYourTurnException e) {
                        notifySingleClient(clientController, new EndMoveResponse("Non è il tuo turno", false));
                    } catch (NotPossibleEndMoveException e) {
                        notifySingleClient(clientController, new EndMoveResponse("Non è possibile terminare il turno", false));
                    }
                    break;

                default:
                    System.out.println("Errore inaspettato");
                    break;
            }
        }
    }

    public  void doAction(ClientController clientController,Point x, String nickname) {
        try {
            try {
                moveHandler.doAction(nickname,x);

                //evaluate if the current player or another player won
                if(moveHandler.isMovement() && match.selectPlayer(nickname).hasWon(match.getBoard(),match.getTurn().getCurrentWorker(),match.getPlayers())){
                    notifyAllClients(new WinNotification(nickname));
                }
                else if(match.outOfTurnWin() != null){
                    notifyAllClients(new WinNotification(match.outOfTurnWin().getNickname()));
                }
                else{
                    notifySingleClient(clientController,new ActionPointResponse("Azione effettuata",true));
                    sendBoard();
                }
            } catch (OpponentEffectException e) {
                notifySingleClient(clientController,new ActionPointResponse("Mossa non consentita da carta avversaria",false));
            } catch (AlreadyBuiltException e) {
                notifySingleClient(clientController,new ActionPointResponse("Hai già costruito",false));
            } catch (IllegalBuildingException e) {
                notifySingleClient(clientController,new ActionPointResponse("Costruzione non valida",false));
            } catch (IllegalMovementException e) {
                notifySingleClient(clientController,new ActionPointResponse("Movimento non valido",false));
            } catch (NotPossibleEndMoveException e) {
                e.printStackTrace();
            } catch (AlreadyWalkedException e) {
                notifySingleClient(clientController,new ActionPointResponse("Hai già mosso",false));
            } catch (endedMoveException e) {
                notifySingleClient(clientController,new ActionPointResponse("Hai terminato già la tua mossa",false));
            } catch (OutOfOrderMoveException e) {
                notifySingleClient(clientController,new ActionPointResponse("Devi prima muovere",false));
            } catch (NoPlayerException e) {
                System.out.println("Errore inaspettato: Non ci sono giocatori");
            }
        } catch (NotYourTurnException e) {
            notifySingleClient(clientController,new ActionPointResponse("Non è il tuo turno",false));
        }
    }

    public int getNumOfPlayers(){
        return clients.size();
    }

    public void disconnectPlayer(ClientController clientController){
        clientController.quitGame();
    }

    public void disconnectAllPlayers(){
        for(ClientController c: clients){
            c.quitGame();
        }
        GameDispatcher gd = GameDispatcher.getInstance();
        gd.removeGame(this);
    }

}
