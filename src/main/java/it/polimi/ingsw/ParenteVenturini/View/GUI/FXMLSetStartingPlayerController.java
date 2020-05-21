package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.AvailablePlayerRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.SetStartingPlayerRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class FXMLSetStartingPlayerController implements ViewController{

    private List<String> players = new ArrayList<>();
    private List<Button> buttonName = new ArrayList<>();

    @FXML
    private VBox starting_player_vbox;

    @FXML
    private void initialize() {
        MessageToServer message = new AvailablePlayerRequest(GUIHandler.clientSideController.getNickname());
        GUIHandler.clientSideController.sendMessage(message);
    }

    public void setPlayers(List<String> players){
        this.players = players;
        displayButtons();
    }

    @FXML
    private void displayButtons(){
        Button btn;
        starting_player_vbox.setSpacing(50);
        for(String player: players){
            btn = new Button();
            btn.getStyleClass().add("player_button");
            String name = player.toLowerCase();
            name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();
            btn.setText(name);
            starting_player_vbox.getChildren().add(btn);
            buttonName.add(btn);
            btn.setOnAction(actionEvent -> sendName(player));
        }

    }

    private void sendName(String selectedPlayerName){
        MessageToServer message = new SetStartingPlayerRequest(GUIHandler.clientSideController.getNickname(), selectedPlayerName);
        GUIHandler.clientSideController.sendMessage(message);
    }

    @Override
    public void displayMessage(String s) {
        //System.out.println("aaaaaa "+s);
    }

    @Override
    public void enableButton() {

    }
}
