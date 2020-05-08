package it.polimi.ingsw.ParenteVenturini.View.GUI;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
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
    private Label info;

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
    private List<Button> workerButtons = new ArrayList<>();
    private List<String> workerindex = new ArrayList<>();

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
        info.setVisible(false);
        move_button.setDisable(true);
        build_button.setDisable(true);
        specialbuild_button.setDisable(true);
        endMove_button.setDisable(true);
        move_button.setOnAction(e -> sendMove("Movement") );
        build_button.setOnAction(e -> sendMove("Construction") );
        specialbuild_button.setOnAction(e -> sendMove("SpecialConstruction") );
        endMove_button.setOnAction(e -> sendMove("EndMove") );
        quit_button.setOnAction(e -> sendQuit());
        nickname.setText(GUIHandler.clientSideController.getNickname().toUpperCase());
    }

    public void fillBoard(Block[][] blocks, List<Point> workers, List<String> colours, List<String> index){
        workerButtons= new ArrayList<>();
        workerindex= new ArrayList<>();
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                stackPanes[i][j].getChildren().clear();

                if(blocks[i][j].getLevel() > 0) {
                    String path = generateBlockIcon(blocks[i][j].getLevel());
                    Image block = new Image(path);
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(105);
                    imageView.setFitWidth(105);
                    imageView.setImage(block);
                    stackPanes[i][j].getChildren().add(imageView);
                }
                if(blocks[i][j].isDome()){
                    String domepath = "/gameobjects/dome.png";
                    Image block = new Image(domepath);
                    ImageView imageView = new ImageView();
                    imageView.setFitHeight(50);
                    imageView.setFitWidth(50);
                    imageView.setImage(block);
                    stackPanes[i][j].getChildren().add(imageView);
                }
                for(Point p: workers) {
                    if (p.equals(i,j)) {
                        String workerpath = generateWorkerIcon( Integer.parseInt(colours.get(workers.indexOf(p))) );
                        if( colours.get(workers.indexOf(p)).equals(""+GUIHandler.clientSideController.getColor()) ){
                            workerButtons.add(buttons[i][j]);
                            workerindex.add(index.get(workers.indexOf(p)));
                        }
                        Image worker= new Image(workerpath);
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

    private String generateBlockIcon(int level){
        switch (level){
            case 1:
                return "/gameobjects/block_1.png";
            case 2:
                return "/gameobjects/block_2.png";
            case 3:
            case 4:
                return "/gameobjects/block_3.png";
        }
        return null;
    }

    private void sendWorker(String index){
        disableWorkerSelection();
        MessageToServer message = new SelectWorkerRequest(GUIHandler.clientSideController.getNickname(),index);
        GUIHandler.clientSideController.sendMessage(message);
    }

    private void sendActionPoints(int x, int y){
        Point point = new Point(x,y);
        MessageToServer message = new ActionPointRequest(point, GUIHandler.clientSideController.getNickname());
        GUIHandler.clientSideController.sendMessage(message);
        clearButtons();
    }

    private void sendQuit(){
        MessageToServer message = new QuitRequest(GUIHandler.clientSideController.getNickname());
        GUIHandler.clientSideController.sendMessage(message);
    }

    public void enableMovebuttons(){
        move_button.setDisable(false);
        build_button.setDisable(false);
        specialbuild_button.setDisable(false);
        endMove_button.setDisable(false);
    }

    public void disableMovebuttons(){
        player_circle.setFill(Color.web("#a7a7a7"));
        move_button.setDisable(true);
        build_button.setDisable(true);
        specialbuild_button.setDisable(true);
        endMove_button.setDisable(true);
    }

    private void sendMove(String type){
        MessageToServer message = new ActionRequest(GUIHandler.clientSideController.getNickname(),type);
        GUIHandler.clientSideController.sendMessage(message);
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
        for(Point p: points){
            int x = p.getX();
            int y = p.getY();

            buttons[x][y].getStyleClass().add("action-button");
            buttons[x][y].setOnAction(e -> sendActionPoints(x,y));
            buttons[x][y].setDisable(false);
        }
    }

    public void enableWorkerSelection(){
        for( Button b: workerButtons){
            b.setDisable(false);
            b.setOnAction(e -> sendWorker(workerindex.get(workerButtons.indexOf(b))));
        }
    }

    public void disableWorkerSelection(){
        for( Button b: workerButtons){
            b.setDisable(true);
        }
    }

    public void setNumTurn(String num){
        turn.setText("Turno "+num);
    }

    public void endGame(){
        MessageToServer message = new EndGameRequest();
        GUIHandler.clientSideController.sendMessage(message);
    }

    public void activePlayerCircle(){
        int color = GUIHandler.clientSideController.getColor();
        if(color>0){
            player_circle.setVisible(true);
            player_circle.setFill(generateColor(color));
        }
    }

    public void displayInfo(String s){
        info.setVisible(true);
        info.setText(s);
    }

    public void disableInfo(){
        info.setVisible(false);
    }

    public void hideButtons(){
        disableInfo();
        info.setVisible(false);
        move_button.setVisible(false);
        build_button.setVisible(false);
        specialbuild_button.setVisible(false);
        endMove_button.setVisible(false);
        quit_button.setVisible(false);
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
