package it.polimi.ingsw.ParenteVenturini.View;

import it.polimi.ingsw.ParenteVenturini.Network.Client.ClientDiProva;
import it.polimi.ingsw.ParenteVenturini.View.GUI.GUIHandler;
import javafx.application.Application;

import static javafx.application.Application.launch;

public class MainClient {
    public static void main(String[] args) {
        if(args.length>0 && args[0].toLowerCase().equals("cli")){
            ClientDiProva c = new ClientDiProva();
            try {
                System.out.println("test");
                c.startClient();
            } catch (Throwable e) {
                e.printStackTrace();
                System.out.println("Disconnected");
            }
        }
        else
            Application.launch(GUIHandler.class, args);
    }
}
