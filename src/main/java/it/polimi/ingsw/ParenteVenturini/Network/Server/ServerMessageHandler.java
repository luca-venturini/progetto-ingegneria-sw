package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;

public interface ServerMessageHandler {
    void visit(AccessGameMessageRequest msg);
    void visit(StoreSelectedCardsRequest msg);
    void visit(AvailableCardRequest msg);
    void visit(SetPlayerCardRequest msg);
    void visit(AvailablePlayerRequest msg);
    void visit(SetStartingPlayerRequest msg);
    void visit(PlaceWorkerRequest msg);
    void visit(AvailablePlaceWorkerPointRequest msg);
    void visit(MovementRequest msg);
    void visit(ConstructionRequest msg);
    void visit(EndMoveRequest msg);
    void visit(SelectWorkerRequest msg);
}
