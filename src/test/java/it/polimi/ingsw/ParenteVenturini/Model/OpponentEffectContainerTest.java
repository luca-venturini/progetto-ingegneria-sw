package it.polimi.ingsw.ParenteVenturini.Model;

import it.polimi.ingsw.ParenteVenturini.Model.Cards.ApolloCard;
import it.polimi.ingsw.ParenteVenturini.Model.Effects.AthenaEffect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpponentEffectContainerTest {
    private Match instance;
    private Player player;
    private OpponentEffectContainer test;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);
        test= instance.getOpponentEffectContainer();
    }

    @Test
    void addEffects_switchToNewTurn() {
        assertNotNull(test.getActiveEffects());
        assertTrue(test.getActiveEffects().isEmpty());
        test.addEffect(new AthenaEffect());
        assertTrue(test.getActiveEffects().isEmpty());
        test.switchToNewTurn();
        assertTrue(!test.getActiveEffects().isEmpty());
    }

    @Test
    void removeMovementPoint() {
    }

    @Test
    void removeConstructionPoint() {
    }

    @Test
    void checkMovementPoint() {
        Point p1= new Point(1,3);
        Point p2= new Point(1,2);
        test.addEffect(new AthenaEffect());
        test.switchToNewTurn();
        player.placeWorker(1,p1,instance.getBoard());
        player.setCard(new ApolloCard());
        test.checkMovementPoint(p2,player.selectWorker(0),instance.getBoard());
    }

    @Test
    void checkConstructionPoint() {
    }
}