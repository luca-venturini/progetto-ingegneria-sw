package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.SelectWorkerRequest;

import java.util.List;

public class SelectWorkerResponse implements MessageToClient{
    private boolean isSet;
    private String message;

    public SelectWorkerResponse(String message, boolean isSet){
        this.isSet = isSet;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSet() {
        return isSet;
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
    public void accept(ClientMessageHandler msgHandler) {
        msgHandler.visit(this);
    }
}
