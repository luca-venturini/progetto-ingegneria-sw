package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.MessageToClient;

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
                msg = (MessageToClient) readStream.readObject();
                if (msg != null)
                    clientSideController.handleMessage(msg);
            } while (msg != null);
            System.out.println("Esco dal do");
        }
        catch (Exception e){

        }
    }
}
