package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.AvailablePlaceWorkerPointRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.PlaceWorkerRequest;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.List;

public class FXMLPlaceWorkerController implements ViewController{

    @FXML
    private Button block_0_0;
    @FXML
    private Button block_1_0;
    @FXML
    private Button block_2_0;
    @FXML
    private Button block_3_0;
    @FXML
    private Button block_4_0;
    @FXML
    private Button block_0_1;
    @FXML
    private Button block_1_1;
    @FXML
    private Button block_2_1;
    @FXML
    private Button block_3_1;
    @FXML
    private Button block_4_1;
    @FXML
    private Button block_0_2;
    @FXML
    private Button block_1_2;
    @FXML
    private Button block_2_2;
    @FXML
    private Button block_3_2;
    @FXML
    private Button block_4_2;
    @FXML
    private Button block_0_3;
    @FXML
    private Button block_1_3;
    @FXML
    private Button block_2_3;
    @FXML
    private Button block_3_3;
    @FXML
    private Button block_4_3;
    @FXML
    private Button block_0_4;
    @FXML
    private Button block_1_4;
    @FXML
    private Button block_2_4;
    @FXML
    private Button block_3_4;
    @FXML
    private Button block_4_4;

    @FXML
    private Label actual_player;


    private Button[][] buttons = new Button[5][5];

    @FXML
    public void initialize(){
        buttons[0][0] = block_0_0;
        buttons[1][0] = block_1_0;
        buttons[2][0] = block_2_0;
        buttons[3][0] = block_3_0;
        buttons[4][0] = block_4_0;
        buttons[0][1] = block_0_1;
        buttons[1][1] = block_1_1;
        buttons[2][1] = block_2_1;
        buttons[3][1] = block_3_1;
        buttons[4][1] = block_4_1;
        buttons[0][2] = block_0_2;
        buttons[1][2] = block_1_2;
        buttons[2][2] = block_2_2;
        buttons[3][2] = block_3_2;
        buttons[4][2] = block_4_2;
        buttons[0][3] = block_0_3;
        buttons[1][3] = block_1_3;
        buttons[2][3] = block_2_3;
        buttons[3][3] = block_3_3;
        buttons[4][3] = block_4_3;
        buttons[0][4] = block_0_4;
        buttons[1][4] = block_1_4;
        buttons[2][4] = block_2_4;
        buttons[3][4] = block_3_4;
        buttons[4][4] = block_4_4;

        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                int finalJ = j;
                int finalI = i;
                buttons[i][j].setOnAction(e -> sendPosition(finalI, finalJ));
                buttons[i][j].setDisable(true);
            }
        }
        requirePossiblePoints();
    }

    public void sendPosition(int x, int y){
        Point point = new Point(x,y);
        MessageToServer message = new PlaceWorkerRequest(point, GUIHandler.clientSideController.getNickanme());
        GUIHandler.clientSideController.sendMessage(message);
        clearButtons();
        System.out.println("TTTTest");
    }

    private void clearButtons(){
        for(int i = 0; i< 5; i++){
            for(int j = 0; j<5; j++){
                buttons[i][j].getStyleClass().remove("grid-button");
                buttons[i][j].setDisable(true);
            }
        }
    }

    public void setCurrentPlayer(String currentPlayer) {
        actual_player.setText(currentPlayer);

    }

    public void requirePossiblePoints(){
        MessageToServer message = new AvailablePlaceWorkerPointRequest();
        GUIHandler.clientSideController.sendMessage(message);
    }

    public void enablePossiblePoints(List<Point> points, String actualPlayer){
        clearButtons();

        for(Point p: points){
            System.out.println(p);
            int x = p.getX();
            int y = p.getY();

            System.out.println("fase 4 "+x+" "+y);

            buttons[x][y].getStyleClass().add("grid-button");
            buttons[x][y].setDisable(false);
        }
        setCurrentPlayer(actualPlayer);


    }

    @Override
    public void displayMessage(String s) {

    }

    @Override
    public void enableButton() {

    }
}
