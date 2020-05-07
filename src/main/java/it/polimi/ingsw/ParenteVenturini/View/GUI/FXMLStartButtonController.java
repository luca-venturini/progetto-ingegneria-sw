package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.View.ViewInterface;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FXMLStartButtonController implements ViewController{

    private ViewInterface guiHandler;

    @FXML
    private Button start_button;


    @FXML
    public void startConnection(Event event){
        GUIHandler gui = (GUIHandler) GUIHandler.clientSideController.getClient();
        gui.loadLogin();
    }


    @Override
    public void displayMessage(String s) {
    }

    @Override
    public void enableButton() {
        //start_button.setDisable(false);
    }
}
