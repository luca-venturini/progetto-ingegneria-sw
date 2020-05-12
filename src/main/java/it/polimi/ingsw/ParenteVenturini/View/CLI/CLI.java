package it.polimi.ingsw.ParenteVenturini.View.CLI;

import it.polimi.ingsw.ParenteVenturini.Model.Block;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientSideController;
import it.polimi.ingsw.ParenteVenturini.Network.Client.ColourPrint;
import it.polimi.ingsw.ParenteVenturini.Network.Client.LightWorker;
import it.polimi.ingsw.ParenteVenturini.Network.MessagesToServer.*;
import it.polimi.ingsw.ParenteVenturini.View.ViewInterface;

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
        String numOfPlayers="";
        boolean done=false;
        try {
            System.out.println("Inserire nickname");
            String name = stdIn.nextLine();
            while (!done) {
                System.out.println("Inserire numero giocatori");
                numOfPlayers = stdIn.nextLine();
                if(numOfPlayers.equals("2") || numOfPlayers.equals("3"))
                    done=true;
            }
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
        boolean done=false;
        String num="";
        int i = 1;
        for (String name: cardsName){
            System.out.println(i+" - "+name);
            i++;
        }

        while(choosen.size()!=numberOfCardsRequired){
            while(!done) {
                System.out.println("numero: ");
                num = stdIn.nextLine();
                try{
                    Integer.parseInt(num);
                    done=true;
                }catch (NumberFormatException e){
                    printString("Carattere inserito non valido");
                }
            }
            if(!choosen.contains(cardsName.get(Integer.parseInt(num)-1)))
                choosen.add(cardsName.get(Integer.parseInt(num)-1));
            else
                System.out.println("Carta già scelta");
            done=false;
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
            if(number.equals("1") || number.equals("2")){
                choice = Integer.parseInt(number);
            }
            else {
                choice=3;
            }
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
        boolean q=false;
        printString("     0      1      2      3      4   ");
        for(int i=0;i<5;i++) {
            printString("  ------------------------------------");
            print(i+" | ");
            for (int j = 0; j < 5; j++) {
                if(blocks[i][j].isDome()){
                    print(" O");
                }
                for(Point p: workers) {
                    if (p.equals(i,j)) {
                        int k = Integer.parseInt(colours.get(workers.indexOf(p)));
                        colourPrint.print(k, "&"+index.get(workers.indexOf(p)));
                        q=true;
                    }
                }
                if(q==false) {
                    print("  ");
                }
                q=false;
                print(" "+blocks[i][j].getLevel()+" | ");
            }
            print("\n");
        }
        printString("  ------------------------------------");
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
            if(number.equals("1") || number.equals("2")){
                choice = Integer.parseInt(number);
            }
            else {
                choice=3;
            }
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
        String xPos="";
        String yPos="";
        boolean done=false;
        printString("--Menu Place Worker setUp--");
        printString("Inizia "+startingPlayer);
        if(nickname.equals(startingPlayer)) {
            int choice;
            do {
                printString("1- Get possible Points");
                printString("2- Place worker");
                printString("Choice: ");
                String number = stdIn.nextLine();
                if(number.equals("1") || number.equals("2")){
                    choice = Integer.parseInt(number);
                }
                else {
                    choice=3;
                }
                if (choice == 1) {
                    MessageToServer message = new AvailablePlaceWorkerPointRequest();
                    clientSideController.sendMessage(message);
                } else if (choice == 2) {
                    while (!done) {
                        printString("x :");
                        xPos = stdIn.nextLine();
                        printString("y :");
                        yPos = stdIn.nextLine();
                        try{
                            Integer.parseInt(xPos);
                            Integer.parseInt(yPos);
                            done=true;
                        }catch (NumberFormatException e){
                            printString("Carattere inserito non valido");
                        }
                    }
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
        String xPos="";
        String yPos="";
        boolean done=false;
        while(!done) {
            printString("Seleziona punto:");
            printString("x :");
            xPos = stdIn.nextLine();
            printString("y :");
            yPos = stdIn.nextLine();
            try{
                Integer.parseInt(xPos);
                Integer.parseInt(yPos);
                done=true;
            }catch (NumberFormatException e){
                printString("Carattere inserito non valido");
            }
        }
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
        boolean k=false;
        printString("    0   1   2   3   4   ");
        for(int i=0;i<5;i++) {
            printString("  ---------------------");
            print(i+" | ");
            for (int j = 0; j < 5; j++) {
                for(Point p: points) {
                    if (p.equals(i,j)) {
                        print(" ");
                        k=true;
                    }
                }
                if(k==false) {
                    print("X");
                }
                k=false;
                print(" | ");
            }
            print("\n");
        }
        printString("  ---------------------");
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
    public void displayWin() {

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
             if(number.equals("1") || number.equals("2") || number.equals("3") || number.equals("4") ){
                 choice = Integer.parseInt(number);
             }
             else {
                 choice=5;
             }
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
                if(number.equals("1") || number.equals("2")){
                    choice = Integer.parseInt(number);
                }
                else {
                    choice=3;
                }
                if(choice ==1 || choice ==2) {
                    MessageToServer message = new SelectWorkerRequest(nickname, number);
                    clientSideController.sendMessage(message);
                }
            }while(choice<1 || choice>2);
        }

    }

}
