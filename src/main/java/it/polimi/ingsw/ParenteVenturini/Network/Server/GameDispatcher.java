package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.InvalidNicknameException;

import java.util.ArrayList;
import java.util.List;

public class GameDispatcher {
    private static GameDispatcher gameDispatcher;
    private List<GameController> gameControllers;
    private GameController gameController;
    private int actualPlayers;
    private int requiredPlayers;

    private GameDispatcher() {
        gameControllers = new ArrayList<>();
    }

    synchronized public static GameDispatcher getInstance(){
        if(gameDispatcher == null) {
            gameDispatcher = new GameDispatcher();
        }
        return gameDispatcher;
    }

    public synchronized GameController getGameController(String nickname, int numOfPlayers) throws InvalidNicknameException {
        if(actualPlayers != 0){
            if (!gameController.isValidNickname(nickname))
                throw new InvalidNicknameException();
        }
        if(actualPlayers==0 ){
            gameController = new GameController(numOfPlayers);
            requiredPlayers = numOfPlayers;
            System.out.println("partite in corso: "+gameControllers.size());
        }
        actualPlayers++;
        if(actualPlayers == requiredPlayers){
            gameControllers.add(gameController);
            actualPlayers = 0;
        }
        return gameController;
    }

}
