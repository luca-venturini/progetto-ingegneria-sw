package it.polimi.ingsw.ParenteVenturini.Model.Effects;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.IllegalBlockUpdateException;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AthenaEffectTest {
    private Match instance;
    private Player player;
    private AthenaEffect testeffect;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);
        testeffect= new AthenaEffect();
    }

    @Test
    void removeMovementPoints() throws IllegalBlockUpdateException {
        Point p1 = new Point(1, 1);
        Point p2 = new Point(1, 0);
        Point p3 = new Point(0, 1);
        Point p4 = new Point(2, 1);
        Point p5 = new Point(2, 2);
        Point p6 = new Point(2, 0);
        Point p7 = new Point(0, 2);
        List<Point> movements = new ArrayList<>();
        movements.add(p2);
        movements.add(p3);
        movements.add(p4);
        movements.add(p5);
        movements.add(p6);
        movements.add(p7);
        instance.getBoard().setBlockLevel(p2, 2);
        instance.getBoard().setBlockLevel(p4, 3);
        instance.getBoard().setBlockLevel(p5, 4);
        instance.getBoard().setBlockLevel(p7, 1);
        assertTrue(!testeffect.removeMovementPoints(movements, p1, instance.getBoard()).isEmpty());
        assertTrue(testeffect.removeMovementPoints(movements, p1, instance.getBoard()).contains(p3));
        assertTrue(!testeffect.removeMovementPoints(movements, p1, instance.getBoard()).contains(p2));
        assertTrue(!testeffect.removeMovementPoints(movements, p1, instance.getBoard()).contains(p4));
        assertTrue(!testeffect.removeMovementPoints(movements, p1, instance.getBoard()).contains(p5));
        assertTrue(!testeffect.removeMovementPoints(movements, p1, instance.getBoard()).contains(p7));

    }
}