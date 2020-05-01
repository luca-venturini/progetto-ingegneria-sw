package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientSideController;
import it.polimi.ingsw.ParenteVenturini.View.CLI.ViewInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GUIHandler extends Application implements ViewInterface {

    private Stage primaryStage;
    public static ClientSideController clientSideController;
    Connection connection;
    private String nickname;

    private FXMLStartButtonController firstPageController;
    private FXMLLoader loader;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void start(Stage stage) throws Exception {
        connection = new Connection();
        connection.connect();

        clientSideController =  connection.getClientSideController();
        clientSideController.setView(this);

        FXMLLoader loader = new FXMLLoader();
        firstPageController = new FXMLStartButtonController();
        loader.setController(firstPageController);

        loader = new FXMLLoader(getClass().getResource("/fxmlFiles/first_page.fxml"));
        Scene scene = new Scene((VBox) loader.load());
        stage.setScene(scene);
        stage.setTitle("Santorini");

        this.primaryStage = stage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    @Override
    public String login() {

        Platform.runLater(() -> {
            ViewController vc = loader.getController();
            vc.enableButton();
        });

        return null;
    }

    public void loadLogin() {

        Platform.runLater(() -> {
            loader = new FXMLLoader(getClass().getResource("/fxmlFiles/loginPage.fxml"));
            Scene scene = null;
            VBox vBox = null;
            try {
                vBox = (VBox) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene = new Scene(vBox);
            primaryStage.setScene(scene);
            primaryStage.show();
        });

    }

    @Override
    public void chooseCards(List<String> cardsName, int numberOfCardsRequired) {
        Platform.runLater(() -> {
            loader = new FXMLLoader(getClass().getResource("/fxmlFiles/selectCards.fxml"));
            Scene scene = null;
            FlowPane flowPane = null;
            try {
                flowPane = (FlowPane) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXMLSelectCardsController controller = loader.getController();
            controller.setCards(cardsName, numberOfCardsRequired);
            scene = new Scene(flowPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        });

    }

    @Override
    public void displayChooseCardMenu() {
        Platform.runLater(() -> {
            loader = new FXMLLoader(getClass().getResource("/fxmlFiles/selectPlayerCard.fxml"));
            Scene scene = null;
            FlowPane flowPane = null;
            try {
                flowPane = (FlowPane) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene = new Scene(flowPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }


    @Override
    public void displayAviableCards(List<String> cards) {
        Platform.runLater(()-> {
            FXMLSelectPlayerCardController controller = loader.getController();
            controller.setCards(cards);
        });
    }

    @Override
    public void updateChooseCardMenu() {

    }

    @Override
    public void waitPage() {
        Platform.runLater(() -> {
            loader = new FXMLLoader(getClass().getResource("/fxmlFiles/waitPage.fxml"));
            Scene scene = null;
            BorderPane pane = null;
            try {
                pane = (BorderPane) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }

    @Override
    public void displayAviablePlayers(List<String> playersName) {
        Platform.runLater(()-> {
            FXMLSetStartingPlayerController controller = loader.getController();
            controller.setPlayers(playersName);
        });
    }

    @Override
    public void updateChooseStartingPlayerMenu() {

    }

    @Override
    public void displayBoard(Block[][] blocks, List<Point> workers, List<String> colours, List<String> index) {

    }

    @Override
    public void displayMenu() {

    }

    @Override
    public void displayMoveMenu() {

    }

    @Override
    public void displaySelectWorker() {

    }

    @Override
    public void displayMessage(String s) {
        Platform.runLater(()-> {
            ViewController controller = loader.getController();
            controller.displayMessage(s);
        });
    }

    @Override
    public void displayChooseStartingPlayerMenu() {
        Platform.runLater(() -> {
            loader = new FXMLLoader(getClass().getResource("/fxmlFiles/set_starting_player.fxml"));
            Scene scene = null;
            VBox vBox = null;
            try {
                vBox = (VBox) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene = new Scene(vBox);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }

    @Override
    public void displayPlaceWorkerMenu() {

    }

    @Override
    public void addLightWorker(Point point) {

    }

    @Override
    public void displaySelectPoint() {

    }

    @Override
    public void startGame(ClientSideController clientSideController) {

    }

    @Override
    public void setController(ClientSideController clientSideController) {

    }

    @Override
    public void closeConnection() {
        connection.quitConnection();
    }

}
