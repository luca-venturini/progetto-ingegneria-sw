package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.IllegalCardException;
import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.InvalidNicknameException;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.ErrorLoginNotification;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.MessageToClient;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.SimplyNotification;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;

/**
 * this controller controls a single client connected
 */
public class ClientController implements ServerMessageHandler {

    /** reference to the class that generated this class */
    private ClientThreadFromServer clientThread;
    /** reference to the game the client is playing */
    private GameController gameController;
    /** reference to the player in the game */
    private Player player;

    /**
     * init the class
     * @param clientThread the thread that handle the client
     */
    public ClientController(ClientThreadFromServer clientThread) {
        this.clientThread = clientThread;
        gameController = null;
    }

    /**
     * send a message to the client
     * @param msg the message that should be send
     */
    public void sendMessage(MessageToClient msg){
        clientThread.sendMessage(msg);
    }


    /**
     * insert the player associated to this controller in the game
     * @param nickname the player's nickname
     * @param numOfPlayers the number of players you want the match to be
     */
    private void insertPlayerInGame(String nickname, String numOfPlayers){
        GameDispatcher gameDispatcher = GameDispatcher.getInstance();
        if(Integer.parseInt(numOfPlayers) != 2 && Integer.parseInt(numOfPlayers) != 3) {
            sendMessage(new ErrorLoginNotification(nickname, "Sono possibili partite solo da 2 o 3 giocatori"));
            return;
        }
        try {
            gameController = gameDispatcher.getGameController(nickname, Integer.parseInt(numOfPlayers));
        } catch (InvalidNicknameException e) {
            sendMessage(new ErrorLoginNotification(nickname, "Nickname non disponibile"));
            return;
        }
        if(gameController.getNumOfPlayers() != 0)
            sendMessage(new SimplyNotification("Partita già inizializzata, sei stato aggiunto a quella"));
        player = gameController.addPlayer(this, nickname);
        gameController.startSetup();
        System.out.println("giocatore aggiunto: "+player.getNickname());
    }

    /**
     * end the game
     */
    public void endGame(){
        if(gameController != null && gameController.isPlaying(player)){
            gameController.disconnectAllPlayers();
        }
        if(gameController != null && !gameController.isPlaying(player)){
            gameController.removeClient(this);
        }
    }

    /**
     * finish playing the game
     */
    public void quitGame(){
        gameController = null;
    }


    /**
     * message received from the client who want to access a new game
     * @param msg the message
     */
    @Override
    public void visit(AccessGameMessageRequest msg) {
        player = null;
        if(gameController != null) return;
        System.out.println("Messaggio arrivato");
        insertPlayerInGame(msg.getNickname(), msg.getValues().get(0));
    }

    /**
     * message received by the challenger who want to store the possible cards
     * @param msg the message
     */
    @Override
    public void visit(StoreSelectedCardsRequest msg) {
        if(gameController == null) return;
        try {
            gameController.addCardsToMatch(msg.getNickname(), msg.getValues());
        } catch (IllegalCardException e) {
            sendMessage(new SimplyNotification("Non è il tuo turno, o non hai selezionata il numero giusto di carte"));
        }
        for (String s: msg.getValues()){
            System.out.println(s);
        }
    }

    /**
     * client message that requests the available cards
     * @param msg the message
     */
    @Override
    public void visit(AvailableCardRequest msg) {
        if(gameController == null) return;
        gameController.sendPossibleCards(this);
    }

    /**
     * client message that requests to set the player's card
     * @param msg the message
     */
    @Override
    public void visit(SetPlayerCardRequest msg) {
        if(gameController == null) return;
        String card = msg.getValues().get(0);
        gameController.setPlayerCard(player, card);
    }

    /**
     * client message that requests the available players
     * @param msg the message
     */
    @Override
    public void visit(AvailablePlayerRequest msg) {
        if(gameController == null) return;
        gameController.sendPossiblePlayers(this);
    }

    /**
     * client message that requests to set the starting player
     * @param msg the message
     */
    @Override
    public void visit(SetStartingPlayerRequest msg) {
        if(gameController == null) return;
        gameController.setStartingPlayer(this.player.getNickname(), msg.getValues().get(0));
    }

    /**
     * client message that requests to place a worker
     * @param msg the message
     */
    @Override
    public void visit(PlaceWorkerRequest msg) {
        if(gameController == null) return;
        if(msg.getNickname().equals(player.getNickname()))
            gameController.placeWorkers(player, msg.getPoint());
    }

    /**
     * client message that request the available cards
     * @param msg the message
     */
    @Override
    public void visit(AvailablePlaceWorkerPointRequest msg) {
        if(gameController == null) return;
        gameController.sendPossibleWorkersSetupPoint(this);
    }

    /**
     * message from the client that want to do an action
     * @param msg the message
     */
    @Override
    public void visit(ActionRequest msg) {
        if(gameController == null) return;
        gameController.doMove(this,msg.getTypeOfAction(),msg.getNickname());
    }

    /**
     * message from client that want to set the worker
     * @param msg the message
     */

    @Override
    public void visit(SelectWorkerRequest msg) {
        if(gameController == null) return;
        gameController.selectWorker(this,msg.getNickname(),msg.getIndex());
    }

    /**
     * message from the client that want to set a new walk or build point
     * @param msg the message
     */
    @Override
    public void visit(ActionPointRequest msg) {
        if(gameController == null) return;
        gameController.doAction(this,msg.getPoint(), msg.getNickname());
    }

    /**
     * message from client that requires to end the game
     * @param msg the message
     */
    @Override
    public void visit(EndGameRequest msg) {
        if(gameController == null) return;
        gameController.disconnectPlayer(this);
    }

    /**
     * message from client that asks to quit the game
     * @param msg the message
     */
    @Override
    public void visit(QuitRequest msg) {
        if(gameController == null) return;
        gameController.manageQuit(msg.getNickname());
    }

    @Override
    public void visit(OtherPlayersRequest msg) {
        if(gameController == null) return;
        gameController.sendOtherPlayersOverview(this);
    }

    /**
     * get the player
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
}