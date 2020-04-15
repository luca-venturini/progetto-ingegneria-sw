package it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientMessageHandler;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageType;

import java.io.Serializable;
import java.util.List;

public interface MessageToClient extends Serializable {

    MessageType getMessageType();
    List<String> getValues();
    void accept(ClientMessageHandler msgHandler);
}
