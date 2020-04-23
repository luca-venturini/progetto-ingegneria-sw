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
    private Thread messageListener;
    private Socket socket;

    public void startClient() throws IOException {


        socket = new Socket(ip, port);
        writeStream = new ObjectOutputStream(socket.getOutputStream());
        readStream = new ObjectInputStream(socket.getInputStream());
        Scanner stdIn = new Scanner(System.in);


        clientSideController = new ClientSideController(stdIn, readStream, writeStream);
        System.out.println("fase 4");
        cli = new CLI(clientSideController);


        clientSideController.setView(cli);
        messageListener = new Thread(new MessageListener(clientSideController, readStream));
        messageListener.start();

        cli.login();

    }

    public void quitClient(){
        try {
            messageListener.join();
            System.out.println("Thread stopped ok");
            socket.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Thread stopped not ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ClientDiProva c = new ClientDiProva();

        try {
            System.out.println("test");
            c.startClient();
            c.quitClient();
            c.startClient();
        } catch (Throwable e) {

            System.out.println("Disconnected");
        }
    }
}