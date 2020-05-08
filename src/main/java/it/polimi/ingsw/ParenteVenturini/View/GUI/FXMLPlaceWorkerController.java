package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.AvailablePlaceWorkerPointRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.PlaceWorkerRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
    private StackPane stackPane_0_0;
    @FXML
    private StackPane stackPane_1_0;
    @FXML
    private StackPane stackPane_2_0;
    @FXML
    private StackPane stackPane_3_0;
    @FXML
    private StackPane stackPane_4_0;
    @FXML
    private StackPane stackPane_0_1;
    @FXML
    private StackPane stackPane_1_1;
    @FXML
    private StackPane stackPane_2_1;
    @FXML
    private StackPane stackPane_3_1;
    @FXML
    private StackPane stackPane_4_1;
    @FXML
    private StackPane stackPane_0_2;
    @FXML
    private StackPane stackPane_1_2;
    @FXML
    private StackPane stackPane_2_2;
    @FXML
    private StackPane stackPane_3_2;
    @FXML
    private StackPane stackPane_4_2;
    @FXML
    private StackPane stackPane_0_3;
    @FXML
    private StackPane stackPane_1_3;
    @FXML
    private StackPane stackPane_2_3;
    @FXML
    private StackPane stackPane_3_3;
    @FXML
    private StackPane stackPane_4_3;
    @FXML
    private StackPane stackPane_0_4;
    @FXML
    private StackPane stackPane_1_4;
    @FXML
    private StackPane stackPane_2_4;
    @FXML
    private StackPane stackPane_3_4;
    @FXML
    private StackPane stackPane_4_4;

    @FXML
    private Label actual_player;

    @FXML
    private Label your_name;

    @FXML
    private Label your_color;

    @FXML
    private Label notifications;

    @FXML
    private Circle your_color_circle;


    private Button[][] buttons = new Button[5][5];
    private StackPane[][] stackPanes = new StackPane[5][5];

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

        stackPanes[0][0] = stackPane_0_0;
        stackPanes[1][0] = stackPane_1_0;
        stackPanes[2][0] = stackPane_2_0;
        stackPanes[3][0] = stackPane_3_0;
        stackPanes[4][0] = stackPane_4_0;
        stackPanes[0][1] = stackPane_0_1;
        stackPanes[1][1] = stackPane_1_1;
        stackPanes[2][1] = stackPane_2_1;
        stackPanes[3][1] = stackPane_3_1;
        stackPanes[4][1] = stackPane_4_1;
        stackPanes[0][2] = stackPane_0_2;
        stackPanes[1][2] = stackPane_1_2;
        stackPanes[2][2] = stackPane_2_2;
        stackPanes[3][2] = stackPane_3_2;
        stackPanes[4][2] = stackPane_4_2;
        stackPanes[0][3] = stackPane_0_3;
        stackPanes[1][3] = stackPane_1_3;
        stackPanes[2][3] = stackPane_2_3;
        stackPanes[3][3] = stackPane_3_3;
        stackPanes[4][3] = stackPane_4_3;
        stackPanes[0][4] = stackPane_0_4;
        stackPanes[1][4] = stackPane_1_4;
        stackPanes[2][4] = stackPane_2_4;
        stackPanes[3][4] = stackPane_3_4;
        stackPanes[4][4] = stackPane_4_4;

        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                int finalJ = j;
                int finalI = i;
                buttons[i][j].setOnAction(e -> sendPosition(finalI, finalJ));
                buttons[i][j].setDisable(true);
            }
        }
        your_name.setText(GUIHandler.clientSideController.getNickname().toUpperCase());
        your_color.setText("");
        your_color_circle.setFill(Color.web("#a7a7a7"));
        requirePossiblePoints();
    }

    public void sendPosition(int x, int y){
        Point point = new Point(x,y);
        MessageToServer message = new PlaceWorkerRequest(point, GUIHandler.clientSideController.getNickname());
        GUIHandler.clientSideController.sendMessage(message);
        clearButtons();
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
        actual_player.setText(currentPlayer.toUpperCase());

    }

    public void requirePossiblePoints(){
        MessageToServer message = new AvailablePlaceWorkerPointRequest();
        GUIHandler.clientSideController.sendMessage(message);
    }

    public void enablePossiblePoints(List<Point> points, String actualPlayer, List<Point> workersPoint, List<Integer> workersColors){
        clearButtons();

        for(Point p: points){
            int x = p.getX();
            int y = p.getY();

            buttons[x][y].getStyleClass().add("grid-button");
            buttons[x][y].setDisable(false);
        }
        for(int i = 0; i<workersPoint.size(); i++){
            Point p = workersPoint.get(i);
            //Circle c = new Circle(40, generateColor(workersColors.get(i)));
            stackPanes[p.getX()][p.getY()].getChildren().clear();
            //stackPanes[p.getX()][p.getY()].getChildren().add(c);//getStyleClass().add(generateColor(workersColors.get(i)));

            String workerpath = generateWorkerIcon( workersColors.get(i) );
            Image worker= new Image(workerpath);
            ImageView workerView = new ImageView();
            workerView.setFitHeight(50);
            workerView.setFitWidth(50);
            workerView.setImage(worker);
            stackPanes[p.getX()][p.getY()].getChildren().add(workerView);

            buttons[p.getX()][p.getY()].setDisable(true);
        }
        setCurrentPlayer(actualPlayer);
    }

    private Color generateColor(int color){
        switch (color){
            case 1:
                return Color.RED;
            case 2:
                return Color.BLUE;
            case 3:
                return Color.GREEN;
        }
        return null;
    }

    private String generateWorkerIcon(int color){
        switch (color){
            case 1:
                return "/gameobjects/redBuilder.png";
            case 2:
                return "/gameobjects/blueBuilder.png";
            case 3:
                return "/gameobjects/greenBuilder.png";
        }
        return null;
    }

    @Override
    public void displayMessage(String s) {
        System.out.println("Arrivata notifica");
        System.out.println("colore: "+GUIHandler.clientSideController.getColor());
        System.out.println("Messaggio: "+s);
        Integer color = GUIHandler.clientSideController.getColor();
        if(color>0){
            your_color.setText(color.toString());
            your_color_circle.setVisible(true);
            your_color_circle.setFill(generateColor(color));
        }

        notifications.setText(s);
    }

    @Override
    public void enableButton() {

    }
}
