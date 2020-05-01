package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.StoreSelectedCardsRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FXMLSelectCardsController implements ViewController{

    private List<String> cards;
    private int numOfCards;
    private Image images[];
    private int currentImage;
    private List<String> chosenCards = new ArrayList<>();

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

    public void setCards(List<String> cards, int numberOfCards){
        this.cards = cards;
        this.numOfCards = numberOfCards;
        currentImage = 0;
        displayCards();
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
        if(chosenCards.size() == numOfCards){
            MessageToServer message = new StoreSelectedCardsRequest(GUIHandler.clientSideController.getNickanme(), chosenCards);
            GUIHandler.clientSideController.sendMessage(message);
            send_button.setDisable(true);
        }
        else{
            chosenCards = new ArrayList<>();
            displayChosenCards();
            showAlert("Devi selezionare esattamente "+ numOfCards +" carte");
        }
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
        String card = cards.get(currentImage);
        if (chosenCards.contains(card)){
            chosenCards.remove(card);
        }
        else{
            chosenCards.add(card);
        }
        displayChosenCards();
    }

    public void displayChosenCards(){
        String text = "";
        for(String s: chosenCards){
            text = text + s + "\n";
        }
        chosen_cards.setText(text);
    }


    @Override
    public void displayMessage(String s) {
        showAlert(s);
    }

    @Override
    public void enableButton() {

    }
}
