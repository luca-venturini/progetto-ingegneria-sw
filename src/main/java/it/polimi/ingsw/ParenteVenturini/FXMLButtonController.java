package it.polimi.ingsw.ParenteVenturini;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FXMLButtonController {

    @FXML
    private Button start_button;

    @FXML
    public void changeColor(Event e){
        System.out.println("Button clicked");
        start_button.setText("Clicked");
    }
}
