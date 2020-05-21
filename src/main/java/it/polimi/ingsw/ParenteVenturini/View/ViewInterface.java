package it.polimi.ingsw.ParenteVenturini.View;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientSideController;

import java.util.List;

/**
 * This interface must be implemented to create a user interface
 */
public interface ViewInterface {
    String login();
    void chooseCards(List<String> cardsName, int numberOfCardsRequired);
    void displayChooseCardMenu();
    void displayBoard(Block[][] blocks, List<Point>workers, List<String>colours, List<String> index);
    void displayMenu();
    void displayMoveMenu();
    void displaySelectWorker();
    void displayMessage(String s);
    void displayChooseStartingPlayerMenu();
    void displayPlaceWorkerMenu(String startingPlayer);
    void displaySelectPoint(List<Point> points);
    void displayEndGame();
    void startGame(ClientSideController clientSideController);
    void closeConnection();
    void displayAviableCards(List<String> cards);
    void updateChooseCardMenu();
    void waitPage();
    void displayAviablePlayers(List<String> playersName);
    void updateChooseStartingPlayerMenu();
    void displayPlaceWorkerPossiblePoints(List<Point> points, String actualPlayer, List<Point> workersPoint, List<Integer> workersColor);
    void updatePlaceWorkerMenu(String s);
    void loadLogin();
    void displayTurn(String num);
    void displayEndMove();
    void displayWin();
    void displayOtherPlayers(List<String> nicknames, List<String> cards, List<Integer> colors);
}
