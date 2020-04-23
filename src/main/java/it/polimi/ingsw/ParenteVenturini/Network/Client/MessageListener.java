package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.MessageToClient;

import java.io.IOException;
import java.io.ObjectInputStream;

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
                //readStream.reset();
                msg = (MessageToClient) readStream.readObject();
                if (msg != null)
                    clientSideController.handleMessage(msg);
            } while (msg != null);
            System.out.println("Esco dal do");
        }
        catch (IOException e){
            try {
                readStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            //e.printStackTrace();
            System.out.println("IOException - Thread listener in esecuzione");
        }
        catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException - Thread listener in esecuzione");
        }

    }
}
