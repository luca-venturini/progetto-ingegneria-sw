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

/**
 * this class controls the conduct of the game
 */
public class GameController {
    /** list of clients */
    private List<ClientController> clients = new ArrayList<>();
    /** the actual match */
    private Match match;
    /** reference to card setup handler */
    private CardSetupHandler cardSetupHandler;
    /** reference to place worker setup handler */
    private PlaceWorkerSetupHandler placeWorkerSetupHandler;
    /** reference to move handler */
    private MoveHandler moveHandler;
    /** reference to the deck */
    private Deck deck = new Deck();

    /**
     * init a new match
     * @param numOfPlayers number of players
     */
    public GameController(int numOfPlayers){
        match = new Match();
        try {
            match.setTypeOfMatch(numOfPlayers);
        } catch (InvalidTypeOfMatch invalidTypeOfMatch) {
            invalidTypeOfMatch.printStackTrace();
        }
        System.out.println("New match with "+numOfPlayers+" players");
    }

    /**
     * add a new player
     * @param client the client
     * @param nickname the nickname
     * @return a reference to the player added
     */
    public synchronized Player addPlayer(ClientController client, String nickname){

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

    /**
     * check the nickname
     * @param nickname the nickname
     * @return true if the nickname is correct
     */
    public synchronized boolean isValidNickname(String nickname){
        return !nickname.equals("") && match.selectPlayer(nickname) == null;
    }

    /**
     * handle startup after player login
     */
    public synchronized void startSetup(){
        if(match.getTypeOfMatch() == clients.size()) {
            notifyAllClients(new SimplyNotification( "Setup phase started, wait..."));
            try {
                match.setChallenger();
            } catch (NoPlayerException e) {
                e.printStackTrace();
            }
            Player challenger = match.getChallenger();
            notifySingleClient(challenger, new SelectCardNotification(deck.getCardNames(), match.getNumPlayers()));
        }
        else
            notifyAllClients(new SimplyNotification( "Wait for other players"));
    }

    /**
     * send messages to all clients
     * @param msg the message
     */
    public synchronized void notifyAllClients(MessageToClient msg){
        for (ClientController client: clients){
            client.sendMessage(msg);
        }
    }

    /**
     * send message to a specific client
     * @param client the client
     * @param msg the message
     */
    public synchronized void notifySingleClient(ClientController client, MessageToClient msg){
        client.sendMessage(msg);
    }

    /**
     * send message to a specific client
     * @param player the player, that has a client associated
     * @param msg the message
     */
    public synchronized void notifySingleClient(Player player, MessageToClient msg){
        for (ClientController c: clients){
            if(c.getPlayer().getNickname().equals(player.getNickname())) {
                c.sendMessage(msg);
                break;
            }
        }
    }

    /**
     * add a new card to the match
     * @param nickname the player nickname
     * @param values the name of the cards
     * @throws IllegalCardException thrown if the given card name doesn't exists
     */
    public synchronized void addCardsToMatch(String nickname, List<String> values) throws IllegalCardException {
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
        else{
            throw new IllegalCardException();
        }
        if(chosenCards.size() == match.getNumPlayers()){
            match.setChosenCards(chosenCards);
            try {
                cardSetupHandler = new CardSetupHandler(chosenCards, match.getPlayers(), match.getChallenger());
            } catch (NoPlayerException e) {
                e.printStackTrace();
            }
            notifyAllClients(new ChooseCardNotification());
            notifyAllClients(new SimplyNotification( "Every player pick a card, starts "+cardSetupHandler.getNextPlayer()));
        }
        else{
            throw new IllegalCardException();
        }
    }

    /**
     * set a card to the player
     * @param player the player
     * @param card the card you want to set
     */
    public synchronized void setPlayerCard(Player player, String card){
        if(cardSetupHandler == null) return;
        if (card == null){
            notifySingleClient(player, new SetPlayerCardResponse( false, "Choose a card"));
        }
        else {
            try {
                cardSetupHandler.setCard(player, deck.selectByName(card));

                if (cardSetupHandler.getNextPlayer() != null) {

                    notifyAllClients(new SimplyNotification(player.getNickname() + " has chosen a card, next: " + cardSetupHandler.getNextPlayer()));
                    notifySingleClient(player, new SetPlayerCardResponse(true, "Card added", card));
                }
                else {
                    //notifyAllClients(new SimplyNotification("Inizio nuova fase, attendi..."));
                    notifySingleClient(player, new SetPlayerCardResponse(true, "Card added", card));
                    notifyAllClients(new WaitNotification());
                    notifySingleClient(match.getChallenger(), new ChooseStartingPlayerNotification());
                }
            } catch (NotYourTurnException e) {
                notifySingleClient(player, new SetPlayerCardResponse(false, "It's not your turn"));
            } catch (IllegalCardException e) {
                notifySingleClient(player, new SetPlayerCardResponse(false, "The card is not available"));
            }
        }
    }

    /**
     * send to the client all the possible cards
     * @param clientController the interested client
     */
    public synchronized void sendPossibleCards(ClientController clientController){
        if(cardSetupHandler == null) return;
        List<String> cardsName = new ArrayList<>();
        if(cardSetupHandler.getPossibleCards().isEmpty()) {
            notifySingleClient(clientController, new SimplyNotification("No card available"));
            return;
        }
        for(Card c: cardSetupHandler.getPossibleCards()){
            cardsName.add(c.getName());
        }
        notifySingleClient(clientController, new AvailableCardResponse(cardsName));
    }


    /**
     * set the player who starts the game
     * @param nickname the nickname of the player who want to set the starting player
     * @param startingPlayerNickname the nickname of the starting player
     */
    public synchronized void setStartingPlayer(String nickname, String startingPlayerNickname){
        if(nickname.equals(match.getChallenger().getNickname())) {
            try {
                match.selectStarter(startingPlayerNickname);
                notifySingleClient(match.getChallenger(), new SetStartingPlayerResponse( true, "Starting player set"));
                notifyAllClients(new SimplyNotification("Each player has to place two workers"));
                System.out.println("Starting player: "+match.getStarter().getNickname());
                placeWorkerSetupHandler = new PlaceWorkerSetupHandler(match.getPlayers(), match.getBoard());
                notifyAllClients(new PlaceWorkersNotification(placeWorkerSetupHandler.getCurrentPlayer().getNickname()));
            } catch (AlreadyChosenStarterException | NoPlayerException e) {
                e.printStackTrace();
                System.out.println("Starting player already set");
            } catch (InvalidNamePlayerException e) {
                notifySingleClient(match.getChallenger(), new SetStartingPlayerResponse( false, "The player is not available"));
            }
        }else{
            System.out.println("Error setStartingPlayer method in gameController");
        }
    }

    /**
     * send a list of the player's nicknames
     * @param clientController the client who will receive the list
     */
    public synchronized void sendPossiblePlayers(ClientController clientController){
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


    /**
     * place the workers on the board
     * @param player the player who want to place the workers
     * @param position the point where the player want to place the workers
     */
    public synchronized void placeWorkers(Player player, Point position){
        if(placeWorkerSetupHandler == null) return;
        Point point = new Point(position.getX(), position.getY());
        try {
            placeWorkerSetupHandler.setWorkerPosition(player, position);
            System.out.println("set point: "+position);
            if(placeWorkerSetupHandler.hasFinished()){
                notifyAllClients(new SimplyNotification("Setup phase completed"));
                notifyAllClients(new SimplyNotification("Starting the game"));
                sendBoard();
                match.setTurn();
                moveHandler= new MoveHandler(this.match);
                notifyYourTurn();
            }
            else if(placeWorkerSetupHandler.getCurrentPlayer().equals(player)) {
                int color = placeWorkerSetupHandler.getCurrentPlayer().selectWorker(0).getColour();
                notifySingleClient(player, new PlaceWorkerResponse(true, false, "First worker set, place the second, please", position, color));
                notifyAllClients(buildAvailablePlaceWorkerPointResponse(placeWorkerSetupHandler.getPossiblePoint(), placeWorkerSetupHandler.getCurrentPlayer().getNickname()));
            }
            else {
                notifySingleClient(player, new PlaceWorkerResponse(true, true, "Second worker placed, wait...", position));
                notifyAllClients(buildAvailablePlaceWorkerPointResponse(placeWorkerSetupHandler.getPossiblePoint(), placeWorkerSetupHandler.getCurrentPlayer().getNickname()));
            }
        } catch (IllegalPlaceWorkerException e) {
            notifySingleClient(player, new PlaceWorkerResponse( false, false, "You can't place the worker here",position ));
        }
    }

    /**
     * send to the client the possibile points where he can place workers
     * @param clientController the client
     */
    public synchronized void sendPossibleWorkersSetupPoint(ClientController clientController){
        if(placeWorkerSetupHandler == null) return;
        List<Point> points = placeWorkerSetupHandler.getPossiblePoint();
        if(placeWorkerSetupHandler.getCurrentPlayer() != null)
            notifySingleClient(clientController, buildAvailablePlaceWorkerPointResponse(points, placeWorkerSetupHandler.getCurrentPlayer().getNickname()) );
        else
            System.out.println("Setup phase ended");
    }

    /**
     * generate a message in which you describe where the player can place the workers and where the others workers are
     * @param points list of possible points
     * @param nickname the player's nickname that will receive the message
     * @return a message about the possible points where to place workers
     */
    private AvailablePlaceWorkerPointResponse buildAvailablePlaceWorkerPointResponse(List<Point> points, String nickname){
        List<Worker> placedWorkers = match.getBoard().getWorkers();
        List<Point> workersPoints = new ArrayList<>();
        List<Integer> workersColors = new ArrayList<>();
        for (Worker w: placedWorkers){
            workersPoints.add(w.getPosition());
            workersColors.add(w.getColour());
        }
        return new AvailablePlaceWorkerPointResponse(points, nickname, workersPoints, workersColors);

    }

    /**
     * send the board updated to the clients
     */
    public synchronized void sendBoard() {
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

    /**
     * notify a client that it is his turn
     */
    public synchronized void notifyYourTurn(){
        moveHandler.setHasDoneAction(false);
        if(match.gameOver()) {
            if(match.getNumPlayers()>2)
                notifySingleClient(match.getTurn().getCurrentPlayer(), new GameOverNotification());
            try {
                manageGameOver();
            } catch (NoPlayerException e) {
                e.printStackTrace();
            }
        }
        else {
            notifyAllClients(new SimplyNotification("It's the turn of " + match.getTurn().getCurrentPlayer().getNickname()));
            notifyAllClients(new TurnNotification(""+match.getTurn().getNumTurn()));
            notifySingleClient(match.getTurn().getCurrentPlayer(), new YourTurnNotification());
            System.out.println("Turn: " + match.getTurn().getNumTurn() + " Player: " + match.getTurn().getCurrentPlayer().getNickname());
        }
    }

    /**
     * menage the gameover, acting differently if the match is played by 2 or 3 players
     * @throws NoPlayerException thrown if there are not players
     */
    public void manageGameOver() throws NoPlayerException {
        if (match.getPlayers().size() == 2) {
            match.getTurn().setNextPlayer();
            notifyAllClients(new WinNotification(match.getTurn().getCurrentPlayer().getNickname()));
            notifySingleClient(match.getTurn().getCurrentPlayer(), new VictoryNotification() );
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            disconnectAllPlayers();
        } else {
            notifyAllClients(new SimplyNotification((match.getTurn().getCurrentPlayer().getNickname()+" lose")));
            Player delplayer=match.getTurn().getCurrentPlayer();
            match.getTurn().setNextPlayer();
            match.deletePlayer(delplayer);
            try {
                match.setTypeOfMatch(2);
            } catch (InvalidTypeOfMatch invalidTypeOfMatch) {
                invalidTypeOfMatch.printStackTrace();
            }
            sendBoard();
            notifyYourTurn();
        }
    }

    /**
     * menage the quit of a player
     * @param nickname the nickname of the player that quit the game
     */
    public void manageQuit(String nickname){
        if(match.getTypeOfMatch() == 2) {
            if (!nickname.equals(match.getTurn().getCurrentPlayer().getNickname())) {
                match.getTurn().setNextPlayer();
            }
            try {
                manageGameOver();
            } catch (NoPlayerException e) {
                e.printStackTrace();
            }
        }
        else {
            if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname)){
                notifySingleClient(match.selectPlayer(nickname), new GameOverNotification());
                notifyAllClients(new SimplyNotification((match.getTurn().getCurrentPlayer().getNickname()+" lose")));
                Player delplayer=match.getTurn().getCurrentPlayer();
                match.getTurn().setNextPlayer();
                match.deletePlayer(delplayer);
                notifyYourTurn();
            }
            else{
                notifySingleClient(match.selectPlayer(nickname), new GameOverNotification());
                notifyAllClients(new SimplyNotification((nickname+" lose")));
                match.deletePlayer(match.selectPlayer(nickname));
            }
            sendBoard();
            try {
                match.setTypeOfMatch(2);
            } catch (InvalidTypeOfMatch invalidTypeOfMatch) {
                invalidTypeOfMatch.printStackTrace();
            }

        }
    }

    /**
     * the player can set the worker he will use during the turn
     * @param clientController the client
     * @param nickname the player's nickname
     * @param index the index of the selected worker
     */
    public synchronized void selectWorker(ClientController clientController, String nickname, int index){
        if(match.getTurn().getCurrentPlayer().getNickname().equals(nickname) && moveHandler != null && !moveHandler.hasDoneAction()){
            match.getTurn().setActualWorker( match.selectPlayer(nickname).selectWorker(index-1) );
            notifySingleClient(clientController,new SelectWorkerResponse("Worker selected",true));
        }
        else notifySingleClient(clientController,new SelectWorkerResponse("It's not your turn, or you've already moved",false));
    }

    /**
     * the client requests to do a move, using a message, this method handle that message and send to the client the possible points for that move
     * @param clientController teh client who requires
     * @param typeOfMove the type of the move (walk, build, specialBuild, endMove)
     * @param nickname the nickname of the player
     */
    public synchronized void doMove(ClientController clientController, String typeOfMove,String nickname){
        if(moveHandler == null) return;
        moveHandler.init();
        List<Point> points;
        if(moveHandler.hasDoneAction() && match.directGameOver()){
            if(match.getNumPlayers()>2)
                notifySingleClient( match.getTurn().getCurrentPlayer(), new GameOverNotification());
            try {
                manageGameOver();
            } catch (NoPlayerException e) {
                e.printStackTrace();
            }
        }
        else {
            switch (typeOfMove) {
                case "Movement":
                    try {
                        points = moveHandler.getMovementsActions(nickname);
                        notifySingleClient(clientController, new ActionResponse(points));
                    } catch (NotYourTurnException e) {
                        notifySingleClient(clientController, new ActionNotification("It's not your turn"));
                    } catch (NoPossibleActionException e) {
                        notifySingleClient(clientController, new ActionNotification(e.getErrorMessage()));
                    } catch (AlreadyWalkedException e) {
                        notifySingleClient(clientController, new ActionNotification("You have already walked"));
                    }
                    break;

                case "Construction":
                    try {
                        points = moveHandler.getConstructionActions(nickname);
                        notifySingleClient(clientController, new ActionResponse(points));
                    } catch (NotYourTurnException e) {
                        notifySingleClient(clientController, new ActionNotification("It's not your turn"));
                    } catch (NoPossibleActionException e) {
                        notifySingleClient(clientController, new ActionNotification(e.getErrorMessage()));
                    } catch (OutOfOrderMoveException e) {
                        notifySingleClient(clientController, new ActionNotification("You must move before building"));
                    } catch (AlreadyBuiltException e) {
                        notifySingleClient(clientController, new ActionNotification("You have already built"));
                    } catch (AlreadyWalkedException e) {
                        e.printStackTrace();
                    }
                    break;

                case "SpecialConstruction":
                    try {
                        points = moveHandler.getSpecialConstructionActions(nickname);
                        notifySingleClient(clientController, new ActionResponse(points));
                    } catch (NotYourTurnException e) {
                        notifySingleClient(clientController, new ActionNotification("It's not your turn"));
                    } catch (NoPossibleActionException e) {
                        notifySingleClient(clientController, new ActionNotification(e.getErrorMessage()));
                    } catch (OutOfOrderMoveException e) {
                        notifySingleClient(clientController, new ActionNotification("You must move before building"));
                    } catch (AlreadyBuiltException e) {
                        notifySingleClient(clientController, new ActionNotification("You hve already built"));
                    } catch (AlreadyWalkedException e) {
                        e.printStackTrace();
                    }
                    break;

                case "EndMove":
                    try {
                        moveHandler.doEndMove(nickname);
                        notifySingleClient(clientController, new EndMoveResponse("Your turn is over", true));
                        notifyYourTurn();
                    } catch (NotYourTurnException e) {
                        notifySingleClient(clientController, new EndMoveResponse("It's not your turn", false));
                    } catch (NotPossibleEndMoveException e) {
                        notifySingleClient(clientController, new EndMoveResponse("You can't end your turn", false));
                    }
                    break;

                default:
                    System.out.println("Errore inaspettato");
                    break;
            }
        }
    }

    /**
     * after the client select the move using the doMove method, he send the point where he want to do the move, this method handle it
     * @param clientController the client who asks to do the action
     * @param x the selected point
     * @param nickname the client nickname
     */
    public synchronized void doAction(ClientController clientController,Point x, String nickname) {
        if(moveHandler == null) return;
        try {
            try {
                moveHandler.doAction(nickname,x);

                //evaluate if the current player or another player won
                if(moveHandler.isMovement() && match.selectPlayer(nickname).hasWon(match.getBoard(),match.getTurn().getCurrentWorker(),match.getPlayers())){
                    notifyAllClients(new WinNotification(nickname));
                    notifySingleClient(clientController, new VictoryNotification() );
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    disconnectAllPlayers();
                }
                else if(match.outOfTurnWin() != null){
                    notifyAllClients(new WinNotification(match.outOfTurnWin().getNickname()));
                    notifySingleClient(clientController, new VictoryNotification() );
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    disconnectAllPlayers();
                }
                else{
                    notifySingleClient(clientController,new ActionPointResponse("Action done",true));
                    sendBoard();
                }
            } catch (OpponentEffectException e) {
                notifySingleClient(clientController,new ActionPointResponse("Move not allowed by an opponent's card",false));
            } catch (AlreadyBuiltException e) {
                notifySingleClient(clientController,new ActionPointResponse("You have already built",false));
            } catch (IllegalBuildingException e) {
                notifySingleClient(clientController,new ActionPointResponse("Build not valid",false));
            } catch (IllegalMovementException e) {
                notifySingleClient(clientController,new ActionPointResponse("Walk not vaild",false));
            } catch (NotPossibleEndMoveException e) {
                e.printStackTrace();
            } catch (AlreadyWalkedException e) {
                notifySingleClient(clientController,new ActionPointResponse("You have already walked",false));
            } catch (endedMoveException e) {
                notifySingleClient(clientController,new ActionPointResponse("You have ended",false));
            } catch (OutOfOrderMoveException e) {
                notifySingleClient(clientController,new ActionPointResponse("Action out of order, remember to move first",false));
            } catch (NoPlayerException e) {
                System.out.println("Errore inaspettato: Non ci sono giocatori");
            }
        } catch (NotYourTurnException e) {
            notifySingleClient(clientController,new ActionPointResponse("It's not your turn",false));
        }
    }

    /**
     * get the number of clients connected
     * @return the number of players connected
     */
    public synchronized int getNumOfPlayers(){
        return clients.size();
    }

    /**
     * disconnect a player from the game
     * @param clientController the client that want to leave the game
     */
    public synchronized void disconnectPlayer(ClientController clientController){
        clients.remove(clientController);
        clientController.quitGame();
        notifySingleClient(clientController, new InterruptedGameNotification());
    }

    /**
     * disconnect all players, for example when the match end
     */
    public synchronized void disconnectAllPlayers(){
        for(ClientController c: clients){
            c.quitGame();
            notifySingleClient(c, new InterruptedGameNotification());
        }
        GameDispatcher gd = GameDispatcher.getInstance();
        gd.removeGame(this);
    }

    /**
     * check if a player is really playing or justa watching the match
     * @param player the player
     * @return true if the player is playing
     */
    public synchronized boolean isPlaying(Player player){
        try {
            if(match.getPlayers().contains(player))
                return true;
        } catch (NoPlayerException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * remove the client from the list of clients
     * @param clientController the client that must be removed
     */
    public synchronized void removeClient(ClientController clientController){
        clients.remove(clientController);
    }

    /**
     * send to a client other players data
     * @param clientController is a reference to the client
     */
    public void sendOtherPlayersOverview(ClientController clientController){
        List<String> names = new ArrayList<>();
        List<String> cards = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        try {
            for(Player p : match.getPlayers()){
                names.add(p.getNickname());
                if(p.getCard() != null) {
                    cards.add(p.getCard().getName());
                }
                else {
                    cards.add("none");
                }
                if(p.getWorkers() != null && p.getWorkers().size()>0)
                    colors.add(p.selectWorker(0).getColour());
                else
                    colors.add(0);
            }
        } catch (NoPlayerException e) {
            e.printStackTrace();
        }
        notifySingleClient(clientController, new OtherPlayersResponse(names, cards, colors));
    }

}
