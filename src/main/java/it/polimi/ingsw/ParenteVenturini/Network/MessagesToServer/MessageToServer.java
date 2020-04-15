package it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer;

import it.polimi.ingsw.ParenteVenturini.Network.Server.ServerMessageHandler;

import java.io.Serializable;
import java.util.List;

public interface MessageToServer extends Serializable {

    MessageType getMessageType();
    List<String> getValues();
    void accept(ServerMessageHandler msgHandler);
}
