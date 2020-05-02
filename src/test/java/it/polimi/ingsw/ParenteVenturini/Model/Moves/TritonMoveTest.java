package it.polimi.ingsw.ParenteVenturini.Model.Moves;

import it.polimi.ingsw.ParenteVenturini.Model.Exceptions.*;
import it.polimi.ingsw.ParenteVenturini.Model.Match;
import it.polimi.ingsw.ParenteVenturini.Model.Player;
import it.polimi.ingsw.ParenteVenturini.Model.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TritonMoveTest {
    private Match instance;
    private Player player;
    private Move tester;

    @BeforeEach
    void setUp() {
        instance= new Match();
        player= new Player("player",instance);
        Point x= new Point(2,2);
        player.placeWorker(1,x,instance.getBoard());
        tester= new TritonMove();
    }

    @Test
    void walk_test1() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyWalkedException.class,()->tester.walk(p2,instance.getBoard(),player.selectWorker(0)));
        assertFalse(tester.forcedMovement());
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.walk(p1,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void walk_test2() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point x= new Point(1,1);
        player.placeWorker(1,x,instance.getBoard());
        Point p1= new Point(0,2);
        Point p2= new Point(0,1);
        tester.walk(p2,instance.getBoard(),player.selectWorker(1));
        tester.walk(p1,instance.getBoard(),player.selectWorker(1));
        assertFalse(tester.forcedMovement());
    }

    @Test
    void build() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        assertThrows(OutOfOrderMoveException.class,()->tester.build(p1,instance.getBoard(),player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(endedMoveException.class,()->tester.walk(p2,instance.getBoard(),player.selectWorker(0)));
        assertThrows(endedMoveException.class,()->tester.build(p2,instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void possibleMovements_test1() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException {
        Point p1= new Point(1,1);
        assertNotNull(tester.possibleMovements(instance.getBoard(), player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyWalkedException.class,()->tester.possibleMovements(instance.getBoard(),player.selectWorker(0)));
    }

    @Test
    void possibleMovements_test2() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException {
        Point p1= new Point(0,1);
        Point p2= new Point(0,2);
        Point x= new Point(1,1);
        player.placeWorker(1,x,instance.getBoard());
        assertNotNull(tester.possibleMovements(instance.getBoard(), player.selectWorker(1)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(1));
        assertNotNull(tester.possibleMovements(instance.getBoard(), player.selectWorker(1)));
        tester.walk(p2,instance.getBoard(),player.selectWorker(1));
        assertThrows(AlreadyWalkedException.class,()->tester.possibleMovements(instance.getBoard(),player.selectWorker(1)));
    }

    @Test
    void possibleBuildings() throws AlreadyWalkedException, AlreadyBuiltException, IllegalBuildingException, endedMoveException, IllegalMovementException, OutOfOrderMoveException {
        Point p1= new Point(1,1);
        Point p2= new Point(2,1);
        assertThrows(OutOfOrderMoveException.class,()->tester.possibleBuildings(instance.getBoard(),player.selectWorker(0)));
        tester.walk(p1,instance.getBoard(),player.selectWorker(0));
        assertNotNull(tester.possibleBuildings(instance.getBoard(), player.selectWorker(0)));
        tester.build(p2,instance.getBoard(),player.selectWorker(0));
        assertThrows(AlreadyBuiltException.class,()->tester.possibleBuildings(instance.getBoard(),player.selectWorker(0)));
    }
}