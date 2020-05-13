package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.View.CLI.CLI;
import it.polimi.ingsw.ParenteVenturini.View.GUI.Connection;
import it.polimi.ingsw.ParenteVenturini.View.ViewInterface;
import it.polimi.ingsw.ParenteVenturini.View.ViewType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class ClientDiProva {
    private int port=1337;
    private String ip = "127.0.0.1";
    private ViewInterface viewInterface;
    private ClientSideController clientSideController;

    public void startClient(){
        Connection connection = new Connection();
        connection.connect(ViewType.CLI);
        clientSideController = connection.getClientSideController();

        viewInterface = new CLI(clientSideController);
        clientSideController.setView(viewInterface);

        viewInterface.startGame(clientSideController);

    }

    public static void main(String[] args) {
        ClientDiProva c = new ClientDiProva();

        try {
            System.out.println("Utente connesso");
            c.startClient();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Disconnected");
        }
    }

}