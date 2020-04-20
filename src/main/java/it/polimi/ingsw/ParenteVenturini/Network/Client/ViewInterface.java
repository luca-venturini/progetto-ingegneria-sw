package it.polimi.ingsw.ParenteVenturini.Network.Client;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Board;
import it.polimi.ingsw.ParenteVenturini.Model.Point;

import java.util.List;

public interface ViewInterface {
    String login();
    void chooseCards(List<String> cardsName, int numberOfCardsRequired);
    void displayChooseCardMenu();
    void displayBoard(Block blocks[][], List<Point>workers, List<String>colours);
    void displayMenu();
    void displayMoveMenu();
    void displaySelectWorker();
    void displayMessage(String s);
    void displayChooseStartingPlayerMenu();
    void displayPlaceWorkerMenu();
    void addLightWorker(Point point);
}
