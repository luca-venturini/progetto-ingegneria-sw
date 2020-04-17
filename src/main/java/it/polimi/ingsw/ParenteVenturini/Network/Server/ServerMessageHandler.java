package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;

public interface ServerMessageHandler {
    void visit(AccessGameMessageRequest msg);
    void visit(StoreSelectedCardsRequest msg);
    void visit(AviableCardRequest msg);
    void visit(SetPlayerCardRequest msg);
}