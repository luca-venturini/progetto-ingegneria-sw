package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Model.Point;

import java.util.List;

public interface ViewInterface {
    String login();
    void chooseCards(List<String> cardsName, int numberOfCardsRequired);
    void displayMenu();
    void displayChooseCardMenu();
    void displayMessage(String s);
    void displayChooseStartingPlayerMenu();
    void displayPlaceWorkerMenu();
    void addLightWorker(LightWorker lightWorker);
    void addLightWorker(Point point);
}
