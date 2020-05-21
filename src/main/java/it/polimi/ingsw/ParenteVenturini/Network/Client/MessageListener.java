package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.MessageToClient;
import it.polimi.ingsw.ParenteVenturini.View.GUI.GUIHandler;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;


/**
 * this class is a thread that wait for server's messages
 */
public class MessageListener implements Runnable {
    ClientSideController clientSideController;
    ObjectInputStream readStream;

    public MessageListener(ClientSideController clientSideController, ObjectInputStream readStream) {
        this.clientSideController = clientSideController;
        this.readStream = readStream;
    }

    @Override
    public void run() {
        try{
            MessageToClient msg;

            do {
                msg = (MessageToClient) readStream.readObject();
                if (msg != null)
                    clientSideController.handleMessage(msg);
            } while (msg != null);
            System.out.println("Exiting do-while");
        }
        catch (IOException e){

            e.printStackTrace();
            System.out.println("Error during the connection");
            Platform.exit();
            System.exit(0);
            /*
            try {
                readStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            clientSideController.closeConnection();
            //e.printStackTrace();
            System.out.println("IOException - Thread listener in esecuzione");
            */
        }
        catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException");
        }

    }
}
