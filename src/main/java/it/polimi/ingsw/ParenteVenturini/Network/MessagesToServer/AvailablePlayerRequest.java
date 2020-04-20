package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.MessageToClient;
import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.util.List;

public class AvailablePlayerRequest implements MessageToServer {

    private String nickname;

    public AvailablePlayerRequest(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public MessageType getMessageType() {
        return null;
    }

    @Override
    public List<String> getValues() {
        return null;
    }

    @Override
    public void accept(ServerMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}
