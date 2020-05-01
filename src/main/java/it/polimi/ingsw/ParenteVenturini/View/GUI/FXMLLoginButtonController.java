package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.AccessGameMessageRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FXMLLoginButtonController implements ViewController{

    @FXML
    private Button login_button;

    @FXML
    private TextField username;

    @FXML
    private TextField playersNumber;

    @FXML
    private Label messageLabel;

    @FXML
    public void initialize() {
        //VBox_login.getChildren().add(new Button("Click me!"));
    }

    @FXML
    public void login(Event event){
        String user = username.getText();
        String number = playersNumber.getText();

        if(user != "" && (number.equals("2") || number.equals("3"))){
            System.out.println("login possibile");
            login_button.setDisable(true);
            MessageToServer message = new AccessGameMessageRequest(user, number);
            GUIHandler.clientSideController.setNickanme(user);
            GUIHandler.clientSideController.sendMessage(message);
        }
        else{
            displayMessage("Inserisci un nickname valido, ricorda che sono possibili solo partite da 2 o 3 giocatori");

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
