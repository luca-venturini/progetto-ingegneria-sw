package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetPlayerCardResponse implements MessageToClient {

    private boolean isSet;
    private String message;
    private String card;

    public SetPlayerCardResponse(boolean isSet, String message) {
        this.isSet = isSet;
        this.message = message;
        this.card = "";
    }

    public SetPlayerCardResponse(boolean isSet, String message, String card) {
        this.isSet = isSet;
        this.message = message;
        this.card = card;
    }

    public String getCard() {
        return card;
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
