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
    void visit(ActionRequest msg);
    void visit(SelectWorkerRequest msg);
    void visit(ActionPointRequest msg);
    void visit(EndGameRequest msg);
    void visit(QuitRequest msg);
    void visit(OtherPlayersRequest msg);
}
