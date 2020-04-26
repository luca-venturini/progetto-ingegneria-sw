package it.polimi.ingsw.ParenteVenturini;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProvaJavaFx extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLButtonController btnController = new FXMLButtonController();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(btnController);

        VBox root = (VBox) loader.load(getClass().getResource("/first_page.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Santorini");

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}