package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.ActionPointRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.ActionRequest;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.MessageToServer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class FXMLGameController implements ViewController{

    @FXML
    private Label nickname;

    @FXML
    private Circle player_circle;

    @FXML
    private TextField messages;

    @FXML
    private Label turn;

    @FXML
    private StackPane StackPane_0_0;

    @FXML
    private Button block_0_0;

    @FXML
    private StackPane StackPane_1_0;

    @FXML
    private Button block_1_0;

    @FXML
    private StackPane StackPane_2_0;

    @FXML
    private Button block_2_0;

    @FXML
    private StackPane StackPane_3_0;

    @FXML
    private Button block_3_0;

    @FXML
    private StackPane StackPane_4_0;

    @FXML
    private Button block_4_0;

    @FXML
    private StackPane StackPane_0_1;

    @FXML
    private Button block_0_1;

    @FXML
    private StackPane StackPane_1_1;

    @FXML
    private Button block_1_1;

    @FXML
    private StackPane StackPane_2_1;

    @FXML
    private Button block_2_1;

    @FXML
    private StackPane StackPane_3_1;

    @FXML
    private Button block_3_1;

    @FXML
    private StackPane StackPane_4_1;

    @FXML
    private Button block_4_1;

    @FXML
    private StackPane StackPane_0_2;

    @FXML
    private Button block_0_2;

    @FXML
    private StackPane StackPane_1_2;

    @FXML
    private Button block_1_2;

    @FXML
    private StackPane StackPane_2_2;

    @FXML
    private Button block_2_2;

    @FXML
    private StackPane StackPane_3_2;

    @FXML
    private Button block_3_2;

    @FXML
    private StackPane StackPane_4_2;

    @FXML
    private Button block_4_2;

    @FXML
    private StackPane StackPane_0_3;

    @FXML
    private Button block_0_3;

    @FXML
    private StackPane StackPane_1_3;

    @FXML
    private Button block_1_3;

    @FXML
    private StackPane StackPane_2_3;

    @FXML
    private Button block_2_3;

    @FXML
    private StackPane StackPane_3_3;

    @FXML
    private Button block_3_3;

    @FXML
    private StackPane StackPane_4_3;

    @FXML
    private Button block_4_3;

    @FXML
    private StackPane StackPane_0_4;

    @FXML
    private Button block_0_4;

    @FXML
    private StackPane StackPane_1_4;

    @FXML
    private Button block_1_4;

    @FXML
    private StackPane StackPane_2_4;

    @FXML
    private Button block_2_4;

    @FXML
    private StackPane StackPane_3_4;

    @FXML
    private Button block_3_4;

    @FXML
    private StackPane StackPane_4_4;

    @FXML
    private Button block_4_4;

    @FXML
    private Button move_button;

    @FXML
    private Button build_button;

    @FXML
    private Button specialbuild_button;

    @FXML
    private Button endMove_button;

    @FXML
    private Button quit_button;

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

        stackPanes[0][0] = StackPane_0_0;
        stackPanes[1][0] = StackPane_1_0;
        stackPanes[2][0] = StackPane_2_0;
        stackPanes[3][0] = StackPane_3_0;
        stackPanes[4][0] = StackPane_4_0;
        stackPanes[0][1] = StackPane_0_1;
        stackPanes[1][1] = StackPane_1_1;
        stackPanes[2][1] = StackPane_2_1;
        stackPanes[3][1] = StackPane_3_1;
        stackPanes[4][1] = StackPane_4_1;
        stackPanes[0][2] = StackPane_0_2;
        stackPanes[1][2] = StackPane_1_2;
        stackPanes[2][2] = StackPane_2_2;
        stackPanes[3][2] = StackPane_3_2;
        stackPanes[4][2] = StackPane_4_2;
        stackPanes[0][3] = StackPane_0_3;
        stackPanes[1][3] = StackPane_1_3;
        stackPanes[2][3] = StackPane_2_3;
        stackPanes[3][3] = StackPane_3_3;
        stackPanes[4][3] = StackPane_4_3;
        stackPanes[0][4] = StackPane_0_4;
        stackPanes[1][4] = StackPane_1_4;
        stackPanes[2][4] = StackPane_2_4;
        stackPanes[3][4] = StackPane_3_4;
        stackPanes[4][4] = StackPane_4_4;

        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                buttons[i][j].setDisable(true);
            }
        }
        move_button.setDisable(true);
        build_button.setDisable(true);
        specialbuild_button.setDisable(true);
        endMove_button.setDisable(true);
        move_button.setOnAction(e -> sendMove("Movement") );
        build_button.setOnAction(e -> sendMove("Construction") );
        specialbuild_button.setOnAction(e -> sendMove("SpecialConstruction") );
        endMove_button.setOnAction(e -> sendMove("EndMove") );
        nickname.setText(GUIHandler.clientSideController.getNickanme().toUpperCase());
    }

    public void fillBoard(Block[][] blocks, List<Point> workers, List<String> colours, List<String> index){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                if(blocks[i][j].getLevel() > 0){
                    Image block= new Image("@../gameobjects/specialbuild.png");
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(105);
                    imageView.setFitWidth(105);
                    imageView.setImage(block);
                    stackPanes[i][j].getChildren().add(imageView);
                    for(Point p: workers) {
                        if (p.equals(i,j)) {
                            String s=generateWorkerIcon( Integer.parseInt(colours.get(workers.indexOf(p))) );
                            Image worker= new Image(s);
                            ImageView workerView = new ImageView();
                            workerView.setFitHeight(50);
                            workerView.setFitWidth(50);
                            workerView.setImage(worker);
                            stackPanes[i][j].getChildren().add(workerView);
                        }
                    }
                }
            }
        }
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
                return "@../gameobjects/redBuilder.png";
            case 2:
                return "@../gameobjects/blueBuilder.png";
            case 3:
                return "@../gameobjects/greenBuilder.png";
        }
        return null;
    }

    private void sendActionPoints(int x, int y){
        Point point = new Point(x,y);
        MessageToServer message = new ActionPointRequest(point, GUIHandler.clientSideController.getNickanme());
        GUIHandler.clientSideController.sendMessage(message);
        clearButtons();
    }

    private void sendMove(String type){
        MessageToServer message = new ActionRequest(GUIHandler.clientSideController.getNickanme(),type);
        GUIHandler.clientSideController.sendMessage(message);
        if(type.equals("EndMove") )
            player_circle.setFill(Color.web("#a7a7a7"));
    }

    private void clearButtons(){
        for(int i = 0; i< 5; i++){
            for(int j = 0; j<5; j++){
                buttons[i][j].getStyleClass().remove("action-button");
                buttons[i][j].setDisable(true);
            }
        }
    }

    public void enableActionPoints(List<Point> points){
        clearButtons();

        for(Point p: points){
            int x = p.getX();
            int y = p.getY();

            buttons[x][y].getStyleClass().add("action-button");
            buttons[x][y].setDisable(false);
        }
    }

    public void setNumTurn(String num){
        turn.setText("Turno "+num);
    }

    public void activePlayerCircle(){
        int color = GUIHandler.clientSideController.getColor();
        if(color>0){
            player_circle.setVisible(true);
            player_circle.setFill(generateColor(color));
        }
    }

    @Override
    public void displayMessage(String s) {
        System.out.println("Arrivata notifica");
        System.out.println("Messaggio: "+s);
        messages.clear();
        messages.setText(s);
    }

    @Override
    public void enableButton() {

    }
}
