package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientSideController;
import it.polimi.ingsw.ParenteVenturini.View.ViewInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class GUIHandler extends Application implements ViewInterface {

    private Stage primaryStage;
    public static ClientSideController clientSideController;
    Connection connection;
    private String nickname;
    private boolean inizializedboard=false;
    private FXMLStartButtonController firstPageController;
    private FXMLLoader loader;

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void start(Stage stage) throws Exception {
        connection = new Connection();
        connection.connect();

        clientSideController = connection.getClientSideController();
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
        inizializedboard=false;
        Platform.runLater(() -> {
            ViewController vc = loader.getController();
            vc.enableButton();
        });
        return null;
    }

    @Override
    public void loadLogin() {
        inizializedboard=false;
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
        Platform.runLater(() -> {
            if(inizializedboard==false) {
                inizializedboard = true;
                loader = new FXMLLoader(getClass().getResource("/fxmlFiles/gameBoard.fxml"));
                Scene scene = null;
                FlowPane flowPane = null;
                try {
                    flowPane = (FlowPane) loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                FXMLGameController myController = loader.getController();
                myController.fillBoard(blocks, workers, colours, index);
                scene = new Scene(flowPane);
                primaryStage.setScene(scene);
                primaryStage.show();
            }
            else {
                FXMLGameController myController = loader.getController();
                myController.fillBoard(blocks, workers, colours, index);
            }
        });
    }


    @Override
    public void displayMenu() {

    }

    @Override
    public void displayMoveMenu() {
        Platform.runLater(()-> {
            FXMLGameController controller = loader.getController();
            controller.displayInfo("Scegli la tua mossa");
            controller.enableMovebuttons();
        });
    }

    @Override
    public void displaySelectWorker() {
        Platform.runLater(()-> {
            FXMLGameController controller = loader.getController();
            controller.displayInfo("Seleziona un worker");
            controller.activePlayerCircle();
            controller.enableWorkerSelection();
        });
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
    public void displayPlaceWorkerMenu(String startingPlayer) {
        Platform.runLater(() -> {
            loader = new FXMLLoader(getClass().getResource("/fxmlFiles/board.fxml"));
            Scene scene = null;
            StackPane stackPane = null;
            try {
                stackPane = (StackPane) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            FXMLPlaceWorkerController myController = loader.getController();
            myController.setCurrentPlayer(startingPlayer);
            scene = new Scene(stackPane);
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }

    @Override
    public void displayPlaceWorkerPossiblePoints(List<Point> points, String actualPlayer, List<Point> workersPoint, List<Integer> workersColor) {
        Platform.runLater(() -> {
            FXMLPlaceWorkerController myController = loader.getController();
            myController.enablePossiblePoints(points, actualPlayer, workersPoint, workersColor);
        });
    }

    @Override
    public void updatePlaceWorkerMenu(String s) {
        Platform.runLater(() -> {
            FXMLPlaceWorkerController myController = loader.getController();
            myController.requirePossiblePoints();
        });
    }

    @Override
    public void displayTurn(String num) {
        Platform.runLater(()-> {
            FXMLGameController controller = loader.getController();
            controller.setNumTurn(num);
        });
    }

    @Override
    public void displayEndMove() {
        Platform.runLater(()-> {
            FXMLGameController controller = loader.getController();
            controller.disableMovebuttons();
            controller.disableInfo();
        });
    }

    @Override
    public void displayWin() {
        Platform.runLater(()-> {
            FXMLLoader modalLoader;
            Stage stage = new Stage();
            modalLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/modalWinMessage.fxml"));
            Scene modalScene = null;
            try {
                modalScene = new Scene((Pane) modalLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Button close = (Button) modalScene.lookup("#closeModal");
            Label textMessage = (Label) modalScene.lookup("#modalMessage");
            textMessage.setText("Hai vinto la partita !");
            close.setOnAction(actionEvent -> stage.close());
            stage.setScene(modalScene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Win Notification");
            stage.setX(primaryStage.getX() + 200);
            stage.setY(primaryStage.getY() + 100);
            stage.show();
        });
    }

    @Override
    public void addLightWorker(Point point) {

    }


    @Override
    public void displaySelectPoint(List<Point> points) {
        Platform.runLater(()-> {
            FXMLGameController controller = loader.getController();
            controller.displayMessage("Seleziona punto in cui fare l'azione");
            controller.enableActionPoints(points);
        });
    }

    @Override
    public void displayEndGame() {
        Platform.runLater(()-> {
            FXMLLoader modalLoader;
            Stage stage = new Stage();
            modalLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/modalExitMessage.fxml"));
            FXMLGameController controller = loader.getController();
            Scene modalScene = null;
            try {
                modalScene = new Scene((Pane) modalLoader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Button siButton = (Button) modalScene.lookup("#siModal");
            Button noButton = (Button) modalScene.lookup("#noModal");
            Label textMessage = (Label) modalScene.lookup("#modalMessage");
            textMessage.setText("Vuoi continuare a vedere la partita?");
            noButton.addEventHandler(ActionEvent.ACTION, actionEvent -> stage.close());
            noButton.addEventHandler(ActionEvent.ACTION, e -> controller.endGame() );
            siButton.addEventHandler(ActionEvent.ACTION, actionEvent -> stage.close());
            siButton.addEventHandler(ActionEvent.ACTION, e -> controller.hideButtons() );
            stage.setScene(modalScene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("End Game");
            stage.setX(primaryStage.getX() + 200);
            stage.setY(primaryStage.getY() + 100);
            stage.show();
        });
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
