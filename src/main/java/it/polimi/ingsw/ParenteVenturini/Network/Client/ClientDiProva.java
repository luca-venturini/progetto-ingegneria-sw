package it.polimi.ingsw.ParenteVenturini.Network.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientDiProva {
    private int port=1337;
    private String ip = "127.0.0.1";
    private ObjectInputStream readStream;
    private ObjectOutputStream writeStream;
    private ClientSideController clientSideController;
    private ViewInterface cli;

    public void startClient() throws IOException {

        Socket socket = new Socket(ip, port);
        writeStream = new ObjectOutputStream(socket.getOutputStream());
        readStream = new ObjectInputStream(socket.getInputStream());
        Scanner stdIn = new Scanner(System.in);


        clientSideController = new ClientSideController(readStream, writeStream, stdIn, socket);
        cli = new CLI(clientSideController);
        clientSideController.setView(cli);

        new Thread(new MessageListener(clientSideController, readStream)).start();
        cli.login();

    }


    public static void main(String[] args) {
        ClientDiProva c = new ClientDiProva();
        try {
            System.out.println("test");
            c.startClient();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}