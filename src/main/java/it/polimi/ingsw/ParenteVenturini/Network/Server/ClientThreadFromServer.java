package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.MessageToClient;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;

import java.io.*;
import java.net.Socket;

/**
 * thread generated from the server to handle single client requests
 */
public class ClientThreadFromServer {
    /** client socket*/
    private Socket clientSocket;
    /** the client controller */
    private ClientController clientController;

    // input and output
    private InputStream inputStream;
    private OutputStream outputStream;

    // used to write and read
    private ObjectInputStream readStream;
    private ObjectOutputStream writeStream;

    /**
     * init the class
     * @param socket the socket
     */
    public ClientThreadFromServer(Socket socket) {
        this.clientSocket = socket;
    }

    /**
     * handle the connection directly with client, reading the messages from it
     */
    public void handleConnection(){
        try{
            // input and output
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();

            writeStream = new ObjectOutputStream(outputStream);
            readStream = new ObjectInputStream(inputStream);


            clientController = new ClientController(this);

            // messages from client
            MessageToServer msg;

            do {
                //readStream.reset();
                msg = (MessageToServer) readStream.readObject();
                if (msg != null)
                    handleMessage(msg);
            } while (msg != null);
            System.out.println("Exiting do - while");
        }
        catch (IOException e){
            System.out.println("Exit");
            e.printStackTrace();
            clientController.endGame();
        } catch (ClassNotFoundException e) {
            System.out.println("Classe not found");
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
    }

    /**
     * close the connection
     */
    public void closeConnection(){
        try {
            readStream.close();
            writeStream.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * handle the received messages
     * @param msg the message
     */
    private void handleMessage(MessageToServer msg){
        msg.accept(clientController);
    }

    /**
     * send a message to the client
     * @param message the message
     */
    public void sendMessage(MessageToClient message){
        try {
            writeStream.reset();
            writeStream.writeObject(message);
            writeStream.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
