package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.MessageToClient;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;

import java.io.*;
import java.net.Socket;

public class ClientThreadFromServer {
    private Socket clientSocket;

    private ClientController clientController;

    // Not used to write or read
    private InputStream inputStream;
    private OutputStream outputStream;

    // To write and read
    private ObjectInputStream readStream;
    private ObjectOutputStream writeStream;

    public ClientThreadFromServer(Socket socket) {
        this.clientSocket = socket;
    }

    public void handleConnection(){
        try{
            // Communication streams
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();

            writeStream = new ObjectOutputStream(outputStream);
            readStream = new ObjectInputStream(inputStream);


            clientController = new ClientController(this);

            // Handle incoming data from client
            MessageToServer msg;

            do {
                msg = (MessageToServer) readStream.readObject();
                if (msg != null)
                    handleMessage(msg);
            } while (msg != null);
            System.out.println("Esco dal do");
        }
        catch (Exception e){
            System.out.println("Exit");
            clientController.endGame();
        }
    }

    public void closeConnection(){
        try {
            readStream.close();
            writeStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleMessage(MessageToServer msg){
        msg.accept(clientController);
    }

    public void sendMessage(MessageToClient message){
        try {
            writeStream.writeObject(message);
            writeStream.flush();
        }
        catch (Exception e){

        }
    }


}
