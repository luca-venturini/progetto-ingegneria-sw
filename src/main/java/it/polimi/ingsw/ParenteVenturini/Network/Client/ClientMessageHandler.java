package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToClient.*;

public interface ClientMessageHandler {
    void visit(ErrorLoginNotification msg);
    void visit(SetUpNotification msg);
    void visit(SelectCardNotification msg);
    void visit(SimplyNotification msg);
    void visit(StartGameNotification msg);
    void visit(ChooseCardNotification msg);
    void visit(SetPlayerCardResponse msg);
    void visit(AvailableCardResponse msg);
    void visit(ChooseStartingPlayerNotification msg);
    void visit(AvailablePlayersResponse msg);
    void visit(SetStartingPlayerResponse msg);
    void visit(PlaceWorkerResponse msg);
    void visit(PlaceWorkersNotification msg);
    void visit(AvailablePlaceWorkerPointResponse msg);
    void visit(BoardUpdateNotification msg);
    void visit(AvailableMovePointResponse msg);
    void visit(SelectWorkerResponse msg);
    void visit(EndMoveResponse msg);
}
