package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.View.CLI.CLI;
import it.polimi.ingsw.ParenteVenturini.View.CLI.ViewInterface;
import it.polimi.ingsw.ParenteVenturini.View.GUI.GUIHandler;
import javafx.application.Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientDiProva {
    private int port=1337;
    private String ip = "127.0.0.1";
    private ObjectInputStream readStream;
    private ObjectOutputStream writeStream;
    private ClientSideController clientSideController;
    private ViewInterface viewInterface;
    private Thread messageListener;
    private Socket socket;
    private ExecutorService pool;

    public void startClient() throws IOException {

        socket = new Socket(ip, port);
        writeStream = new ObjectOutputStream(socket.getOutputStream());
        readStream = new ObjectInputStream(socket.getInputStream());
        Scanner stdIn = new Scanner(System.in);

        clientSideController = new ClientSideController(stdIn, readStream, writeStream);
        System.out.println("fase 4");
        viewInterface = new CLI(clientSideController);

        clientSideController.setView(viewInterface);

        messageListener = new Thread(new MessageListener(clientSideController, readStream));
        messageListener.start();

        viewInterface.startGame(clientSideController);

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
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Disconnected");
        }
    }

}