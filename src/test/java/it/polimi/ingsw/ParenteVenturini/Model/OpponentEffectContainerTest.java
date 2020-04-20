package it.polimi.ingsw.ParenteVenturini.Model;

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
    }

    @Test
    void checkConstructionPoint() {
    }
}