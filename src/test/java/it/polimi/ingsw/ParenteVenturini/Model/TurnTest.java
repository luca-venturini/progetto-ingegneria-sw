package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.AlreadyPresentPlayerException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoMorePlayersException;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.NoPlayerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnTest {
    private Match instance;
    private Turn tester;

    @BeforeEach
    void setUp() throws NoMorePlayersException, AlreadyPresentPlayerException, NoPlayerException {
        instance= new Match();
        instance.addPlayer("player1");
        instance.addPlayer("player2");
        tester= new Turn(instance.getPlayers(),instance.getOpponentEffectContainer());
    }

    @Test
    void setNextPlayer() {
        assertTrue(tester.getNumTurn()==1);
        assertTrue(tester.getCurrentPlayer().getNickname().equals("player1"));
        tester.setNextPlayer();
        assertTrue(tester.getCurrentPlayer().getNickname().equals("player2"));
        assertTrue(tester.getNumTurn()==1);
        tester.setNextPlayer();
        assertTrue(tester.getNumTurn()==2);
        assertTrue(tester.getCurrentPlayer().getNickname().equals("player1"));
    }

    @Test
    void setActualWorker() throws NoPlayerException {
        assertNull(tester.getCurrentWorker());
        Point p= new Point(0,0);
        instance.getPlayers().get(0).placeWorker(1,p,instance.getBoard());
        tester.setActualWorker(instance.getPlayers().get(0).selectWorker(0));
        assertNotNull(tester.getCurrentWorker());
    }

}