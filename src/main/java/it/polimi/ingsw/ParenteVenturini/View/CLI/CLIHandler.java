package it.polimi.ingsw.ParenteVenturini.View.CLI;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientSideController;
import it.polimi.ingsw.ParenteVenturini.Network.Client.Connection;
import it.polimi.ingsw.ParenteVenturini.View.ViewInterface;
import it.polimi.ingsw.ParenteVenturini.View.ViewType;

public class CLIHandler {
    private int port=1337;
    private String ip = "127.0.0.1";
    private ViewInterface viewInterface;
    private ClientSideController clientSideController;

    public void startClient(){
        Connection connection = new Connection();
        connection.connect(ViewType.CLI);
        clientSideController = connection.getClientSideController();

        viewInterface = new CLI(clientSideController);
        clientSideController.setView(viewInterface);

        viewInterface.startGame(clientSideController);

    }

    public static void main(String[] args) {
        CLIHandler c = new CLIHandler();

        try {
            System.out.println("Utente connesso");
            c.startClient();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Disconnected");
        }
    }

}