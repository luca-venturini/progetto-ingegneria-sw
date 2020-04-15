package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.ArrayList;
import java.util.List;

public class SimplyNotification implements MessageToClient {

    private List<String> notification = new ArrayList<>();
    private MessageType messageType = MessageType.NOTIFICATION;

    public SimplyNotification(String stringMessage) {
        notification.add(stringMessage);
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }


    @Override
    public List<String> getValues() {
        return notification;
    }

    @Override
    public void accept(ClientMessageHandler msgVisitor) {
        msgVisitor.visit(this);
    }
}
