package it.polimi.ingsw.ParenteVenturini.View.CLI;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientSideController;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ColourPrint;
import it.polimi.ingsw.ParenteVenturini.Network.Client.LightWorker;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI implements ViewInterface {

    private Scanner stdIn = new Scanner(System.in);
    private ClientSideController clientSideController;
    private String nickname;
    private List<LightWorker> lightWorkers;
    private ColourPrint colourPrint;

    public CLI(ClientSideController clientInMessageHandler) {
        this.clientSideController = clientInMessageHandler;
        this.lightWorkers = new ArrayList<>();
        this.colourPrint = new ColourPrint();
    }

    @Override
    public String login(){
        try {
            System.out.println("Inserire nickname");
            String name = stdIn.nextLine();
            System.out.println("Inserire numero giocatori");
            String numOfPlayers = stdIn.nextLine();
            MessageToServer message = new AccessGameMessageRequest(name, numOfPlayers);
            clientSideController.sendMessage(message);
            System.out.println("Message inviato");
            System.out.println("Il tuo nickname è: "+name);
            nickname = name;
            return name;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void chooseCards(List<String> cardsName, int numberOfCardsRequired) {
        System.out.println("Digita il numero delle carte che vuoi usare:");
        List<String> choosen = new ArrayList<>();

        int i = 1;
        for (String name: cardsName){
            System.out.println(i+" - "+name);
            i++;
        }

        while(choosen.size()!=numberOfCardsRequired){
            System.out.println("numero: ");
            String num = stdIn.nextLine();
            if(!choosen.contains(cardsName.get(Integer.parseInt(num)-1)))
                choosen.add(cardsName.get(Integer.parseInt(num)-1));
            else
                System.out.println("Carta già scelta");
        }

        MessageToServer message = new StoreSelectedCardsRequest(nickname, choosen);
        clientSideController.sendMessage(message);
        System.out.println("Message inviato");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printString(String s){
        System.out.println(s);
    }

    private void print(String s){
        System.out.print(s);
    }


    @Override
    public void displayMenu(){
        while(true) {
            printString("--Menu--");
            printString("1- Scegli la tua carta");
            printString("2- Posiziona operai");
            printString("Scelta: ");
            stdIn.nextLine();
        }
    }

    @Override
    public void displayChooseCardMenu() {
        int choice;
        do {
            printString("--Menu card SetUp--");
            printString("1- Get possible Cards");
            printString("2- Choose and send your card");
            printString("Choice: ");
            String number = stdIn.nextLine();
            choice = Integer.parseInt(number);
            if (choice == 1) {
                MessageToServer message = new AvailableCardRequest(nickname);
                clientSideController.sendMessage(message);
            } else if (choice == 2) {
                printString("card name:");
                String card = stdIn.nextLine();
                MessageToServer message = new SetPlayerCardRequest(nickname, card);
                clientSideController.sendMessage(message);
            }
        }while(choice<1 || choice>2);
    }

    @Override
    public void displayBoard(Block[][] blocks, List<Point>workers, List<String>colours, List<String>index) {
        printString("        0            1            2           3            4   ");
        for(int i=0;i<5;i++) {
            printString("  ------------------------------------------------------------------");
            print(i+" | ");
            for (int j = 0; j < 5; j++) {
                if(blocks[i][j].isDome()){
                    print("O");
                }
                else {
                    print(" ");
                }
                for(Point p: workers) {
                    if (p.equals(i,j)) {
                        int k = Integer.parseInt(colours.get(workers.indexOf(p)));
                        colourPrint.print(k, "&"+index.get(workers.indexOf(p)));
                    }
                    else {
                        print("  ");
                    }
                }
                print(" "+blocks[i][j].getLevel()+"| ");
            }
            print("\n");
        }
        printString("  ------------------------------------------------------------------");
    }

    @Override
    public void displayMessage(String s) {
        printString(s);
    }

    @Override
    public void displayChooseStartingPlayerMenu() {
        int choice;
        do {
            printString("--Menu starting Player SetUp--");
            printString("1- Get possible Players");
            printString("2- Choose starting player");
            printString("Choice: ");
            String number = stdIn.nextLine();
            choice = Integer.parseInt(number);
            if (choice == 1) {
                MessageToServer message = new AvailablePlayerRequest(nickname);
                clientSideController.sendMessage(message);
            } else if (choice == 2) {
                printString("player name:");
                String playerName = stdIn.nextLine();
                MessageToServer message = new SetStartingPlayerRequest(nickname, playerName);
                clientSideController.sendMessage(message);
            }
        }while(choice<1 || choice>2);
    }

    @Override
    public void displayPlaceWorkerMenu(String startingPlayer) {
        printString("--Menu Place Worker setUp--");
        printString("Inizia "+startingPlayer);
        if(nickname.equals(startingPlayer)) {
            int choice;
            do {
                printString("1- Get possible Points");
                printString("2- Place worker");
                printString("Choice: ");
                String number = stdIn.nextLine();
                choice = Integer.parseInt(number);
                if (choice == 1) {
                    MessageToServer message = new AvailablePlaceWorkerPointRequest();
                    clientSideController.sendMessage(message);
                } else if (choice == 2) {
                    printString("x :");
                    String xPos = stdIn.nextLine();
                    printString("y :");
                    String yPos = stdIn.nextLine();
                    Point point = new Point(Integer.parseInt(xPos), Integer.parseInt(yPos));
                    MessageToServer message = new PlaceWorkerRequest(point, nickname);
                    clientSideController.sendMessage(message);
                }
            } while (choice < 1 || choice > 2);
        }
    }

    @Override
    public void addLightWorker(Point point) {
        lightWorkers.add(new LightWorker(point));
    }

    @Override
    public void displaySelectPoint(List<Point> points) {
        printString("Seleziona punto:");
        printString("x :");
        String xPos = stdIn.nextLine();
        printString("y :");
        String yPos = stdIn.nextLine();
        Point point = new Point(Integer.parseInt(xPos), Integer.parseInt(yPos));
        MessageToServer message = new ActionPointRequest(point, nickname);
        clientSideController.sendMessage(message);
    }

    @Override
    public void startGame(ClientSideController clientSideController) {
        nickname = login();
        clientSideController.setNickname(nickname);
    }

    @Override
    public void setController(ClientSideController clientSideController) {

    }

    @Override
    public void closeConnection() {

    }

    @Override
    public void displayAviableCards(List<String> cards) {
        for(String s: cards)
            displayMessage(s);
        displayChooseCardMenu();
    }

    @Override
    public void updateChooseCardMenu() {
        displayChooseCardMenu();
    }

    @Override
    public void waitPage() {
        System.out.println("Attendi...");
    }

    @Override
    public void displayAviablePlayers(List<String> playersName) {
        for(String s: playersName)
            displayMessage(s);
        displayChooseStartingPlayerMenu();
    }

    @Override
    public void updateChooseStartingPlayerMenu() {
        displayChooseStartingPlayerMenu();
    }

    @Override
    public void displayPlaceWorkerPossiblePoints(List<Point> points, String actualPlayer, List<Point> workersPoint, List<Integer> workersColor) {
        printString(points.toString());
        if(nickname.equals(actualPlayer))
            displayPlaceWorkerMenu(actualPlayer);
    }

    @Override
    public void updatePlaceWorkerMenu(String s) {
        displayPlaceWorkerMenu(s);
    }

    @Override
    public void loadLogin() {
        System.out.println("INIZIA UNA NUOVA PARTITA!");
        startGame(clientSideController);
    }

    @Override
    public void displayTurn(String num) {

    }

    @Override
    public void displayEndMove() {

    }

    @Override
    public void displayEndGame() {
        printString("Vuoi continuare a vedere la partita?");
        printString("Scegli (y o n):");
        String choice = stdIn.nextLine();
        if(!choice.equals("y")){
            MessageToServer message = new EndGameRequest();
            clientSideController.sendMessage(message);
        }
    }

    @Override
    public void displayMoveMenu() {
        int choice;
         do{
            printString("--Menu Worker's move--");
            printString("1- Movement");
            printString("2- Construction");
            printString("3- Special Construction");
            printString("4- End Move");
            printString("Scelta: ");
            String number= stdIn.nextLine();
            choice = Integer.parseInt(number);
            if (choice == 1) {
                MessageToServer message = new ActionRequest(nickname,"Movement");
                clientSideController.sendMessage(message);
            } else if (choice == 2) {
                MessageToServer message = new ActionRequest(nickname,"Construction");
                clientSideController.sendMessage(message);
            } else if (choice == 3) {
                MessageToServer message = new ActionRequest(nickname,"SpecialConstruction");
                clientSideController.sendMessage(message);
            }
            else if(choice == 4){
                MessageToServer message = new ActionRequest(nickname,"EndMove");
                clientSideController.sendMessage(message);
            }
        }while(choice<1 || choice>4);
    }

    @Override
    public void displaySelectWorker() {
        {
            int choice;
            do {
                printString("Seleziona il worker (1 o 2): ");
                printString("1- Worker 1");
                printString("2- Worker 2");
                printString("Choice: ");
                String number = stdIn.nextLine();
                choice = Integer.parseInt(number);
                if(choice ==1 || choice ==2) {
                    MessageToServer message = new SelectWorkerRequest(nickname, number);
                    clientSideController.sendMessage(message);
                }
            }while(choice<1 || choice>2);
        }

    }

}
