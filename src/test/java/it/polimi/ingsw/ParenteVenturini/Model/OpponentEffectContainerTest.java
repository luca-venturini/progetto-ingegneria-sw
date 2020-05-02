package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.ApolloCard;
import it.polimi.ingsw.ParenteVenturini.Model.Cards.AthenaCard;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.AthenaEffect;
import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpponentEffectContainerTest {
    private Match instance;
    private Player player;
    private Player player1;
    private Player player2;
    private OpponentEffectContainer test;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);

        /*
        instance.addPlayer("player1");
        instance.addPlayer("player2");

        player1.setCard(new ApolloCard());
        player2.setCard(new AthenaCard());
        player1.placeWorker(1, new Point(0,0), instance.getBoard());
        player1.placeWorker(1, new Point(1,1), instance.getBoard());
        player2.placeWorker(1, new Point(0,0), instance.getBoard());
        player2.placeWorker(1, new Point(1,1), instance.getBoard());
        instance.setTurn();
        */
        test = instance.getOpponentEffectContainer();
    }

    @Test
    void addEffects_switchToNewTurn() {
        assertNotNull(test.getActiveEffects());
        assertTrue(test.getActiveEffects().isEmpty());
        test.addEffect(new AthenaEffect());
        assertTrue(test.getActiveEffects().isEmpty());
        test.switchToNewTurn();
        assertFalse(test.getActiveEffects().isEmpty());
    }

    @Test
    void checkMovementPoint() throws IllegalBlockUpdateException {
        Point p1= new Point(1,3);
        Point p2= new Point(1,2);
        test.addEffect(new AthenaEffect());
        test.switchToNewTurn();
        player.placeWorker(1,p1,instance.getBoard());
        player.setCard(new ApolloCard());
        assertTrue(test.checkMovementPoint(p2,player.selectWorker(0),instance.getBoard()));
        instance.getBoard().setBlockLevel(p2,1);
        assertFalse(test.checkMovementPoint(p2,player.selectWorker(0),instance.getBoard()));
    }

    @Test
    void checkConstructionPoint() {
        Point p1= new Point(1,3);
        Point p2= new Point(1,2);
        test.addEffect(new AthenaEffect());
        test.switchToNewTurn();
        player.placeWorker(1,p1,instance.getBoard());
        player.setCard(new ApolloCard());
        assertTrue(test.checkConstructionPoint(p2,player.selectWorker(0),instance.getBoard()));

    }

}