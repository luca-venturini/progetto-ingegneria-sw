package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.*;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientSideController implements ClientMessageHandler {

    private ObjectInputStream readStream;
    private ObjectOutputStream writeStream;
    private Scanner stdIn;
    private Socket socket;
    private ViewInterface client;

    public ClientSideController(ObjectInputStream readStream, ObjectOutputStream writeStream, Scanner stdIn, Socket socket) {
        this.readStream = readStream;
        this.writeStream = writeStream;
        this.socket = socket;
        this.stdIn = stdIn;
    }

    public void setView(ViewInterface client){
        this.client = client;
    }


    public void handleMessage(MessageToClient msg){
        msg.accept(this);
    }

    @Override
    public void visit(ErrorLoginNotification msg) {
        System.out.println(msg.getNickname()+": "+msg.getValues().get(0));
        client.login();
    }

    @Override
    public void visit(SetUpNotification msg) {
        System.out.println("Fase di setUp iniziata");
    }

    @Override
    public void visit(SelectCardNotification msg) {
        client.chooseCards(msg.getValues(), msg.numberOfCardsRequired());
    }

    @Override
    public void visit(SimplyNotification msg) {
        System.out.println(msg.getValues().get(0));
    }

    @Override
    public void visit(StartGameNotification msg) {
        client.displayMenu();
    }


    public void sendMessage(MessageToServer msg){
        try {
            writeStream.writeObject(msg);
            writeStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
