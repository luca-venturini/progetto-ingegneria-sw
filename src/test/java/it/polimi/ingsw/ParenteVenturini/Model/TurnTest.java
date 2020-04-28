package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.ApolloCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.ChronusCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.HestiaCard;
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
        instance.selectPlayer("player1").setCard(new ChronusCard());
        instance.selectPlayer("player2").setCard(new HestiaCard());
        tester= new Turn(instance.getPlayers(),instance.getOpponentEffectContainer());
    }

    @Test
    void setNextPlayer() {
        assertEquals(1, tester.getNumTurn());
        assertEquals("player1", tester.getCurrentPlayer().getNickname());
        tester.setNextPlayer();
        assertEquals("player2", tester.getCurrentPlayer().getNickname());
        assertEquals(1, tester.getNumTurn());
        tester.setNextPlayer();
        assertEquals(2, tester.getNumTurn());
        assertEquals("player1", tester.getCurrentPlayer().getNickname());
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