package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.AvailableCardRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.SetPlayerCardRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.StoreSelectedCardsRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class FXMLSelectPlayerCardController implements ViewController{

    private List<String> cards;
    private Image images[];
    private int currentImage;
    private String chosenCard;

    @FXML
    private void initialize() {
        MessageToServer message = new AvailableCardRequest(GUIHandler.clientSideController.getNickanme());
        GUIHandler.clientSideController.sendMessage(message);
    }

    @FXML
    private ImageView card_image_view;

    @FXML
    private Button left_button;

    @FXML
    private Button right_button;

    @FXML
    private Button send_button;

    @FXML
    private Label chosen_cards;

    public void setCards(List<String> cards){
        currentImage = 0;
        chosenCard = "";
        chosen_cards.setText("");
        if(cards != null && cards.size()>0) {
            this.cards = cards;
            displayCards();
        }
    }

    private void displayCards(){
        images = new Image[cards.size()];
        for (int i = 0; i < cards.size(); i++) {
            images[i] = new Image("/cards/"+cards.get(i)+".png");
        }
        displayImage(currentImage);
    }

    private void displayImage(int num){
        card_image_view.setImage(images[num]);
    }

    @FXML
    public void leftClick(){
        if(currentImage == 0)
            currentImage = cards.size()-1;
        else
            currentImage--;
        displayImage(currentImage);
    }

    @FXML
    public void rightClick(){
        if(currentImage == cards.size()-1)
            currentImage = 0;
        else
            currentImage++;
        displayImage(currentImage);
    }

    @FXML
    public void send_cards(){
        MessageToServer message = new SetPlayerCardRequest(GUIHandler.clientSideController.getNickanme(), chosenCard);
        GUIHandler.clientSideController.sendMessage(message);
    }

    private void showAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, msg, ButtonType.OK);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            //do stuff
        }
    }

    @FXML
    public void toggleCard(){
        String selectedCard = cards.get(currentImage);
        chosenCard = selectedCard;
        displayChosenCards();
    }

    public void displayChosenCards(){
        chosen_cards.setText(chosenCard);
    }


    @Override
    public void displayMessage(String s) {
        showAlert(s);
        MessageToServer message = new AvailableCardRequest(GUIHandler.clientSideController.getNickanme());
        GUIHandler.clientSideController.sendMessage(message);
    }

    @Override
    public void enableButton() {

    }
}
