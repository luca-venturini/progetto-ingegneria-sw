package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.List;

public class EndMoveResponse implements MessageToClient {
    private boolean done;
    private String message;

    public EndMoveResponse(String message, boolean done){
        this.done = done;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isDone() {
        return done;
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

    }
}
