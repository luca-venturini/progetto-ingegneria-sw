package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeraEffectTest {
    private Match instance;
    private Player player;
    private HeraEffect testeffect;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);
        Point p1 = new Point(1, 1);
        Point p2 = new Point(0, 1);
        player.placeWorker(1,p1,instance.getBoard());
        player.placeWorker(1,p2,instance.getBoard());
        testeffect= new HeraEffect();
    }

    @Test
    void isWinEffect() {
        assertTrue(testeffect.isWinEffect());
    }

    @Test
    void isWinner() {
        assertTrue(testeffect.isWinner(instance.getBoard(),player.selectWorker(0)));
        assertFalse(testeffect.isWinner(instance.getBoard(),player.selectWorker(1)));
    }

    @Test
    void getEffectCode() {
        assertTrue(testeffect.getEffectCode() instanceof String);
    }

}