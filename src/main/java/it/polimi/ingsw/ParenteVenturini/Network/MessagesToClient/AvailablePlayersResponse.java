package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class AvailablePlayersResponse implements MessageToClient {
    private List<String> players;

    public AvailablePlayersResponse(List<String> cards) {
        this.players = cards;
    }

    @Override
    public MessageType getMessageType() {
        return null;
    }

    @Override
    public List<String> getValues() {
        return players;
    }

    @Override
    public void accept(ClientMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}

