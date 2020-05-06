package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.AccessGameMessageRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLLoginButtonController implements ViewController{
    ObservableList<String> options = FXCollections.observableArrayList("2","3");

    @FXML
    private Button login_button;

    @FXML
    private TextField username;

    @FXML
    private Label messageLabel;

    @FXML
    private ComboBox comboBoxPalyerNumber;

    @FXML
    public void initialize() {
        //VBox_login.getChildren().add(new Button("Click me!"));
        comboBoxPalyerNumber.setItems(options);
        comboBoxPalyerNumber.getSelectionModel().select("2");
    }

    @FXML
    public void login(Event event){
        String user = username.getText();
        String number = comboBoxPalyerNumber.getSelectionModel().getSelectedItem().toString();

        if((number.equals("2") || number.equals("3"))){
            System.out.println("login possibile");
            login_button.setDisable(true);
            MessageToServer message = new AccessGameMessageRequest(user, number);
            GUIHandler.clientSideController.setNickname(user);
            GUIHandler.clientSideController.sendMessage(message);
        }
        else{
            displayMessage("Nickname non disponibile");
        }
    }

    @Override
    public void displayMessage(String s) {
        messageLabel.setText(s);
    }

    @Override
    public void enableButton() {
        login_button.setDisable(false);
    }

}
