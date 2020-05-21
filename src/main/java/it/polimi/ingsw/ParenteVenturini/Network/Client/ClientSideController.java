package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.*;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;
import it.polimi.ingsw.ParenteVenturini.View.ViewInterface;
import it.polimi.ingsw.ParenteVenturini.View.GUI.GUIHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * this client side controller handle the messages arrived form the server,
 * handling the User Interface, no matter if the user choose GUI or CLI
 */
public class ClientSideController implements ClientMessageHandler {

    private ObjectInputStream readStream;
    private ObjectOutputStream writeStream;
    private Scanner stdIn;
    private ViewInterface client;
    private ViewInterface gui;
    private String nickname;
    private int color;
    private String yourCard;
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public ClientSideController(Scanner stdIn, ObjectInputStream readStream, ObjectOutputStream writeStream) {
        this.writeStream = writeStream;
        this.readStream = readStream;
        this.stdIn = stdIn;
    }

    public String getYourCard() {
        return yourCard;
    }

    public int getColor() {
        return color;
    }

    public void clearColor(){
        color = 0;
    }

    public void setGui(GUIHandler gui) {
        this.gui = gui;
    }

    public ViewInterface getClient() {
        return client;
    }

    public void printhello(){
        client.login();
    }

    public void setView(ViewInterface client){
        this.client = client;
    }

    //handle the messages using Visitor pattern
    public void handleMessage(MessageToClient msg){
        msg.accept(this);
    }


    /**
     * send messages to the server
     * @param msg
     */
    public void sendMessage(MessageToServer msg){
        try {
            writeStream.reset();
            writeStream.writeObject(msg);
            writeStream.flush();
        } catch (IOException e) {
            System.out.println("Error sending the message - connection closed");
            closeConnection();
        }
    }

    public void closeConnection(){
        try {
            writeStream.close();
            readStream.close();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        finally {
            connection.quitConnection();
            //client.closeConnection();
        }
    }


    @Override
    public void visit(ErrorLoginNotification msg) {
        client.displayMessage(msg.getNickname()+": "+msg.getValues().get(0));
        client.login();
    }

    @Override
    public void visit(SetUpNotification msg) {
        color = 0;
        client.displayMessage("Starting setup phase");
    }

    @Override
    public void visit(SelectCardNotification msg) {
        client.chooseCards(msg.getValues(), msg.numberOfCardsRequired());
    }

    //Text notification
    @Override
    public void visit(SimplyNotification msg) {
        client.displayMessage(msg.getValues().get(0));
    }

    @Override
    public void visit(StartGameNotification msg) {
        client.displayMenu();
    }

    @Override
    public void visit(ChooseCardNotification msg) {
        client.displayChooseCardMenu();
    }

    @Override
    public void visit(SetPlayerCardResponse msg) {
        if(msg.isSet())
            yourCard = msg.getCard();
        client.displayMessage(msg.getValues().get(0));
        if(! msg.isSet())
            client.updateChooseCardMenu();
    }

    /*
    @Override
    public void visit(AvailableCardResponse msg) {
        for(String s: msg.getValues())
            client.displayMessage(s);
        client.displayChooseCardMenu();
    }

     */


    @Override
    public void visit(AvailableCardResponse msg) {
        client.displayAviableCards(msg.getValues());
        /*
        for(String s: msg.getValues())
            client.displayMessage(s);
        client.displayChooseCardMenu();
         */
    }

    @Override
    public void visit(ChooseStartingPlayerNotification msg) {
        client.displayChooseStartingPlayerMenu();
    }

    @Override
    public void visit(AvailablePlayersResponse msg) {
        client.displayAviablePlayers(msg.getValues());
        /*
        for(String s: msg.getValues())
            client.displayMessage(s);
        client.displayChooseStartingPlayerMenu();
         */
    }

    @Override
    public void visit(SetStartingPlayerResponse msg) {
        client.displayMessage(msg.getValues().get(0));
        if(! msg.isSet()) {
            client.updateChooseStartingPlayerMenu();
            //System.out.println("Giocatore non settato");
        }
    }

    @Override
    public void visit(PlaceWorkersNotification msg) {
        client.displayPlaceWorkerMenu(msg.getStartingPlayer());
    }

    @Override
    public void visit(AvailablePlaceWorkerPointResponse msg) {
        client.displayPlaceWorkerPossiblePoints(msg.getPoints(), msg.getActualPlayer(), msg.getWorkersPoint(), msg.getWorkersColor());
    }

    @Override
    public void visit(BoardUpdateNotification msg) {
        Block [][] b = msg.getBlocks();
        /*
        for(int i = 0; i<4; i++){
            for(int j = 0; j<4; j++){
                System.out.println("level: "+b[i][j].getLevel());
            }
        }

         */
        client.displayBoard(msg.getBlocks(),msg.getWorkerpositions(),msg.getColours(),msg.getIndex());
    }

    @Override
    public void visit(SelectWorkerResponse msg) {
        client.displayMessage(msg.getMessage());
        if(!msg.isSet())
            client.displaySelectWorker();
        else
            client.displayMoveMenu();
    }

    @Override
    public void visit(EndMoveResponse msg) {
        client.displayMessage(msg.getMessage());
        if(!msg.isDone())
            client.displayMoveMenu();
        else {
            client.displayMessage("Your turn is over. Wait...");
            client.displayEndMove();
        }
    }

    @Override
    public void visit(YourTurnNotification msg) {
        client.displayMessage("It's your turn");
        client.displaySelectWorker();
    }

    @Override
    public void visit(WinNotification msg) {
        client.displayMessage("The winner is: "+msg.getMessage());

    }

    //Response to a request of points
    @Override
    public void visit(ActionResponse msg) {
        client.displayMessage(msg.getPoints().toString());
        client.displaySelectPoint(msg.getPoints());
    }

    //Response to a movement
    @Override
    public void visit(ActionPointResponse msg) {
        client.displayMessage(msg.getMessage());
        client.displayMoveMenu();
    }

    @Override
    public void visit(ActionNotification msg) {
        if(msg.getMessage().equals("It's not your turn")){
            client.displayMessage(msg.getMessage());
        }
        else if(msg.getMessage().equals("Nessuna azione possibile. Seleziona un altro worker") ){
            client.displayMessage(msg.getMessage());
            client.displaySelectWorker();
        }
        else{
            client.displayMessage(msg.getMessage());
            client.displayMoveMenu();
        }
    }

    @Override
    public void visit(GameOverNotification msg) {
        client.displayMessage("You lose");
        client.displayEndGame();
    }

    @Override
    public void visit(WaitNotification msg) {
        client.waitPage();
    }

    // If the game is interrupted by a disconnection
    @Override
    public void visit(InterruptedGameNotification msg) {
        client.loadLogin();
        client.displayMessage("The game is over");
    }

    @Override
    public void visit(TurnNotification msg) {
        client.displayTurn(msg.getnum());
    }

    @Override
    public void visit(VictoryNotification msg) {
        client.displayWin();
    }


    // make the UI to display the other players and their cards
    @Override
    public void visit(OtherPlayersResponse msg) {
        client.displayOtherPlayers(msg.getNicknames(), msg.getPlayersCards(), msg.getPlayersColors());
    }

    @Override
    public void visit(PlaceWorkerResponse msg) {
        System.out.println(msg.getSettedPoint().toString());
        if(msg.isSet()) {
            //client.addLightWorker(new LightWorker(msg.getSettedPoint() ));
            //client.addLightWorker(msg.getSettedPoint());
            //System.out.println("fatto");
        }
        if(msg.getColor() > 0){
            this.color = msg.getColor();
        }

        client.displayMessage(msg.getMessage());
        if(!msg.isHasFinished() && !msg.isSet()) {
            client.updatePlaceWorkerMenu(nickname);
        }

    }
}
