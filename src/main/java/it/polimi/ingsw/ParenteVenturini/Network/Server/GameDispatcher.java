package it.polimi.ingsw.ParenteVenturini.Network.Server;

import it.polimi.ingsw.ParenteVenturini.Network.Exceptions.InvalidNicknameException;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that handle the creation of new games, keeping track of active games
 */

public class GameDispatcher {
    private static GameDispatcher gameDispatcher;
    private List<GameController> gameControllers;
    private GameController gameController;
    private int actualPlayers;
    private int requiredPlayers;

    /**
     * init the class
     */
    private GameDispatcher() {
        gameControllers = new ArrayList<>();
    }

    /**
     * get an istance of the class
     * @return the class
     */
    synchronized public static GameDispatcher getInstance(){
        if(gameDispatcher == null) {
            gameDispatcher = new GameDispatcher();
        }
        return gameDispatcher;
    }

    /**
     * create of get the available game controller
     * @param nickname the nickname of the player who requests the gameController
     * @param numOfPlayers the number of players the match must be, if the gameController hasn't been initialized yet
     * @return the gameController
     * @throws InvalidNicknameException thrown if the nickname is not valid
     */
    public synchronized GameController getGameController(String nickname, int numOfPlayers) throws InvalidNicknameException {
        if(nickname.equals(""))
            throw new InvalidNicknameException();
        if(actualPlayers != 0){
            if (!gameController.isValidNickname(nickname))
                throw new InvalidNicknameException();
        }
        if(actualPlayers==0 ){
            gameController = new GameController(numOfPlayers);
            requiredPlayers = numOfPlayers;
            System.out.println("Matches already started: "+gameControllers.size());
        }
        actualPlayers++;
        if(actualPlayers == requiredPlayers){
            gameControllers.add(gameController);
            actualPlayers = 0;
        }
        return gameController;
    }

    /**
     * remove the gameController when a match ends
     * @param gc the game controller that must be removed
     */
    public synchronized void removeGame(GameController gc){
        if(gameControllers.contains(gc))
            gameControllers.remove(gc);
        else{
            gameController = null;
            actualPlayers = 0;
        }
    }

}
