package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.Arrays;
import java.util.List;

public class ErrorLoginNotification implements MessageToClient {

    private MessageType messageType = MessageType.NOTIFICATION;
    private String nickname;
    private String message;

    public ErrorLoginNotification(String nickname, String message) {
        this.nickname = nickname;
        this.message = message;
    }

    @Override
    public MessageType getMessageType() {
        return messageType;
    }


    public String getNickname() {
        return nickname;
    }

    @Override
    public List<String> getValues() {
        return Arrays.asList(message);
    }

    @Override
    public void accept(ClientMessageHandler msgVisitor) {
        msgVisitor.visit(this);
    }
}
