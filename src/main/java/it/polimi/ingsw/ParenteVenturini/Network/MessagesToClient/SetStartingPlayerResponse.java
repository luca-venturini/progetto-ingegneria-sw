package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.Arrays;
import java.util.List;

public class SetStartingPlayerResponse implements MessageToClient {
    private boolean isSet;
    private String message;

    public SetStartingPlayerResponse(boolean isSet, String message) {
        this.isSet = isSet;
        this.message = message;
    }


    @Override
    public MessageType getMessageType() {
        return null;
    }

    @Override
    public List<String> getValues() {
        return Arrays.asList(message);
    }

    public boolean isSet(){
        return isSet;
    }

    @Override
    public void accept(ClientMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}
