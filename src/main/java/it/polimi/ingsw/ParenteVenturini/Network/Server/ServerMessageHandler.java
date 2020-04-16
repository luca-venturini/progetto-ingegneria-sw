package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoPlayerException;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;

public interface ServerMessageHandler {
    void visit(AccessGameMessageRequest msg);
    void visit(SelectCardRequest msg);
}
