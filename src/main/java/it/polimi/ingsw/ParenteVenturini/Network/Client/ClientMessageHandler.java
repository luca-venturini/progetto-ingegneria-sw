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
    void visit(AviableCardResponse msg);
}
