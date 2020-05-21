package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.StoreSelectedCardsRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FXMLSelectCardsController implements ViewController{

    private List<String> cards;
    private int numOfCards;
    private Image images[];
    private int currentImage;
    private List<String> chosenCards = new ArrayList<>();
    private List<Label> chosenCardsLabel = new ArrayList<>();

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

    @FXML
    private VBox chosenCardsVBox;

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
            MessageToServer message = new StoreSelectedCardsRequest(GUIHandler.clientSideController.getNickname(), chosenCards);
            GUIHandler.clientSideController.sendMessage(message);
            send_button.setDisable(true);
        }
        else{
            chosenCards = new ArrayList<>();
            displayChosenCards();
            showAlert("You must select "+ numOfCards +" cards");
        }
    }

    private void showAlert(String msg){
        FXMLLoader modalLoader;
        Stage stage = new Stage();
        modalLoader = new FXMLLoader(getClass().getResource("/fxmlFiles/modalMessage.fxml"));
        Scene modalScene = null;
        try {
            modalScene = new Scene((Pane) modalLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Button close = (Button) modalScene.lookup("#closeModal");
        Label textMessage = (Label) modalScene.lookup("#modalMessage");
        textMessage.setText(msg);
        close.setOnAction(actionEvent -> stage.close());
        stage.setScene(modalScene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("Info");
        stage.show();
    }

    @FXML
    public void toggleCard(){
        String card = cards.get(currentImage);
        if (chosenCards.contains(card)){
            chosenCards.remove(card);
        }
        else{
            if(chosenCards.size()>=numOfCards) {
                chosenCards.remove(0);
            }
            chosenCards.add(chosenCards.size(),card);
        }
        displayChosenCards();
    }

    public void displayChosenCards(){
        for(Label label: chosenCardsLabel){
            chosenCardsVBox.getChildren().remove(label);
        }
        chosenCardsLabel = new ArrayList<>();
        for(String s: chosenCards){
            Label cardName = new Label(s.toUpperCase());
            cardName.getStyleClass().add("card-name-style");
            chosenCardsVBox.getChildren().add(cardName);
            chosenCardsLabel.add(cardName);
        }
    }


    @Override
    public void displayMessage(String s) {
        showAlert(s);
    }

    @Override
    public void enableButton() {

    }
}
